package com.yupi.pkapigateaway;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.PkAPICommmon.model.entity.InterfaceInfo;
import com.PkAPICommmon.model.entity.User;
import com.PkAPICommmon.service.InnerInterfaceInfoService;
import com.PkAPICommmon.service.InnerUserInterfaceInfoService;
import com.PkAPICommmon.service.InnerUserService;
import com.itpk.pkapiclientsdk.Utils.signUtil;
import com.sun.xml.internal.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;

import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;



@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {
    private   static final  List<String>  IP_WHITE_LIST = Arrays.asList("0:0:0:0:0:0:0:1","127.0.0.1");
    /**
     * 全局过滤
     */
    @DubboReference
    private InnerInterfaceInfoService innerInterfaceInfoService;
    @DubboReference
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;
    @DubboReference
    private InnerUserService innerUserService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("custom global filter");
//        1.用户发送请求到网关
        ServerHttpRequest request = exchange.getRequest();
        String path = "http://localhost:8091"+request.getPath().value();
        String method = request.getMethod().toString();
        log.info("请求唯一标识: "+ request.getId());
        log.info("请求路径: "+ request.getPath().value());
        log.info("请求方法: "+ request.getMethod());
        log.info("请求参数: "+ request.getQueryParams());
        log.info("请求来源地址: "+ request.getLocalAddress().getHostString());
        log.info("请求目标地址: "+ request.getRemoteAddress());
//        2.请求日志
        ServerHttpResponse response = exchange.getResponse();
        if (!IP_WHITE_LIST.contains(request.getLocalAddress().getHostString())) {
         return NoAuth(response);
        }
//        3.黑白名单
        String Timestamp=request.getHeaders().getFirst("timestamp");
        String body=request.getHeaders().getFirst("body");
        String accessKey=request.getHeaders().getFirst("accessKey");
        String nonce=request.getHeaders().getFirst("nonce");
        String sign=request.getHeaders().getFirst("sign");
        TODO 应该是根据ak到数据库中查询sk;
        User InvokeUser=null;
        try {
           InvokeUser= innerUserService.getInvokeUser(accessKey);
           if(InvokeUser==null){
               log.error("未找到调用接口用户");
               return NoAuth(response);
           }
        }
        catch (Exception e) {
            log.error("getInvokeUser error:{}",e.getMessage());
            return HandleInvokeError(response);
        }
        String secretKey=InvokeUser.getSecretKey();
        String serveSign= signUtil.genSign(body, secretKey);
        //校验ak
        if(!InvokeUser.getAccessKey().equals(accessKey)){
           return NoAuth(response);
        }
        //判断过期
       Long currentTime=System.currentTimeMillis()/1000;
        Long FIVE_MINUTES=5*60L;
        if((currentTime-Long.parseLong(Timestamp)>=FIVE_MINUTES)){
            return NoAuth(response);
        }
        //判断随机数
        if(Long.parseLong(nonce)>10000L)
        {
            return NoAuth(response);
        }
        //判断签名
        if(!serveSign.equals(sign))
        {
           return NoAuth(response);

        }
//        4.用户鉴权（判断ak，sk是否合法）
        TODO 从数据库中查询接口是否存在和方法是否匹配;
        InterfaceInfo interfaceInfo = null;
        try {
           interfaceInfo= innerInterfaceInfoService.getInterfaceInfo(path, method);
           if (interfaceInfo==null)
           {
               log.error("未找到接口路由:{}",path);
               return HandleInvokeError(response);
           }
        }
        catch (Exception e) {
            log.error("interfaceInfo error:{}",e.getMessage());
            return HandleInvokeError(response);
        }
        Long userId = InvokeUser.getId();
        Long interfaceInfoId = interfaceInfo.getId();
        if (!innerUserInterfaceInfoService.CheckUserInterfaceInfo(userId,interfaceInfoId))
        {
            log.error("该用户无法调用该接口：未分配次数或者次数不足");
            return NoAuth(response);
        }
//        5.请求的模拟接口是否存在
        Mono<Void> filter = chain.filter(exchange);
//        6.请求转发，调用模拟接口
     return handleResponse(exchange,chain,interfaceInfoId,userId);
//
////        9.调用失败，返回错误规范的错误码
//        return filter;
    }


    public Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain,long interfaceId,long userId) {
        try {
            //拿到response对象
            ServerHttpResponse originalResponse = exchange.getResponse();
            //缓存数据
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            //拿到响应码
            HttpStatus statusCode = originalResponse.getStatusCode();
            if (statusCode != HttpStatus.OK) {
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            //往返回直里面写数据
                            return super.writeWith(
                           fluxBody.map(dataBuffers -> {
                               try {
                                   innerUserInterfaceInfoService.InvokeInterfaceCount(userId, interfaceId);
                               }
                               catch (Exception e) {
                                   log.error("InvokeInterfaceCount error:{}", e.getMessage());
                               }
                               //7.调用成功invokeCount+1
                                    byte[] content = new byte[dataBuffers.readableByteCount()];
                                    dataBuffers.read(content);
                                    DataBufferUtils.release(dataBuffers);//释放掉内容
                                    //构建日志
                                    StringBuilder sb2 = new StringBuilder(200);
                                    sb2.append("<-- {} {}\n");
                                    String data = new String(content, StandardCharsets.UTF_8);
                                    // 调用成功,调用次数加一;
                            sb2.append(data);
                                    log.info("响应结果"+data);
                                    return bufferFactory.wrap(content);
                                }));
                        } else {
                            log.error("<-- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange);
        } catch (Exception e) {
            log.error("网关处理响应异常");
            return chain.filter(exchange);
        }}

//————————————————
//
//    版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
//
//    原文链接：https://blog.csdn.net/qq_19636353/article/details/126759522



 private Mono<Void> NoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
      return   response.setComplete();
 }
    private Mono<Void> HandleInvokeError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return   response.setComplete();
    }
    @Override
    public int getOrder() {
        return -1;
    }
}