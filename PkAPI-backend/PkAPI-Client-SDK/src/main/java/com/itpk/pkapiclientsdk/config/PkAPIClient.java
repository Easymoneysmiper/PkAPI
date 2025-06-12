package com.itpk.pkapiclientsdk.config;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;

import cn.hutool.json.JSONUtil;
import com.itpk.pkapiclientsdk.model.User;

import java.util.HashMap;
import java.util.Map;

import static com.itpk.pkapiclientsdk.Utils.signUtil.genSign;


public class PkAPIClient {

    public static final   String TO_HOST="http://localhost:8091/api";
    private String accessKey;
    private String secretKey;

    public PkAPIClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    private  Map<String,String> getHeader(String body)
    {
        Map<String,String> header = new HashMap<>();
        header.put("accessKey",accessKey);
        header.put("nonce",RandomUtil.randomNumbers(4));
        header.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
        header.put("body",body);
        header.put("sign",genSign(body,secretKey));
        return header;
    }

    private static final String BASE_URL = TO_HOST+"/name";

    /**
     * GET请求测试
     * @param name 名称参数
     * @return 服务器响应
     */
    public  String NameByGet(String name) {
        String url = BASE_URL + "/get?name=" + name;
        return HttpRequest.get(url).addHeaders(getHeader(name)).execute().body();
    }

    /**
     * POST表单请求测试
     * @param name 名称参数
     * @return 服务器响应
     */
    public String NameByPost(String name) {
        return HttpRequest.post(BASE_URL + "/post?name=" + name)
                .form("name", name)
                .addHeaders(getHeader(JSONUtil.toJsonStr(name)))
                .execute()
                .body();
    }

    /**
     * POST JSON请求测试
     * @param user 用户对象
     * @return 服务器响应
     */
    public  String NameByPostJson(User user) {
        return HttpRequest.post(BASE_URL + "/user")
                .body(JSONUtil.toJsonStr(user))
                .addHeaders(getHeader(JSONUtil.toJsonStr(user)))
                .execute()
                .body();
    }}
