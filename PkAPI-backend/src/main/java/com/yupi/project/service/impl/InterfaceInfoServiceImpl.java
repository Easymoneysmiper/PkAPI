package com.yupi.project.service.impl;

import com.PkAPICommmon.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.project.common.ErrorCode;
import com.yupi.project.exception.BusinessException;
import com.yupi.project.service.InterfaceInfoService;
import com.yupi.project.mapper.InterfaceInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.util.Arrays;
import java.util.List;




/**
* @author easymoneysniper
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2025-04-08 20:43:46
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{



    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口信息不能为空");
        }

        // 根据操作类型校验ID
        if (add) {
            if (interfaceInfo.getId() != null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "新增时ID必须为空");
            }
        } else {
            if (interfaceInfo.getId() == null || interfaceInfo.getId() <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "ID不合法");
            }
        }

        // 校验接口名称（1-50字符）
        String name = interfaceInfo.getName();
        if (StringUtils.isBlank(name)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口名称不能为空");
        }
        if (name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口名称不能超过50字符");
        }

   if(!add) {     // 校验用户ID
       Long userId = interfaceInfo.getUserId();
       if (userId == null || userId <= 0) {
           throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户ID不合法");
       }
       // 校验接口状态（0/1）
       Integer status = interfaceInfo.getStatus();
       if (status == null) {
           throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口状态不能为空");
       }
       if (status != 0 && status != 1) {
           throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口状态不合法（0-开放，1-关闭）");
       }
   }
        // 校验接口路由（符合路径格式）
        String url = interfaceInfo.getUrl();
        if (StringUtils.isBlank(url)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口路由不能为空");
        }
//        if (!url.matches("^/[a-zA-Z0-9_\\-/]*$")) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口路由格式不正确（需以/开头）");
//        }

        // 校验请求方法（GET/POST/PUT/DELETE/PATCH）
        String method = interfaceInfo.getMethod();
        List<String> validMethods = Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH");
        if (StringUtils.isBlank(method) || !validMethods.contains(method.toUpperCase())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求方法不合法");
        }
        interfaceInfo.setMethod(method.toUpperCase());



        // 校验接口描述（不超过512字符）
        String description = interfaceInfo.getDescription();
        if (StringUtils.isNotBlank(description) && description.length() > 512) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口描述不能超过512字符");
        }

        // 使用GSON校验JSON格式（请求头）
        String requestHeader = interfaceInfo.getRequestHeader();
        if (StringUtils.isNotBlank(requestHeader)) {
            try {
                JsonParser.parseString(requestHeader);
            } catch (JsonSyntaxException e) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求头必须是有效JSON格式");
            }
        }

        // 使用GSON校验JSON格式（响应头）
        String responseHeader = interfaceInfo.getResponseHeader();
        if (StringUtils.isNotBlank(responseHeader)) {
            try {
                JsonParser.parseString(responseHeader);
            } catch (JsonSyntaxException e) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "响应头必须是有效JSON格式");
            }
        }
    }
}




