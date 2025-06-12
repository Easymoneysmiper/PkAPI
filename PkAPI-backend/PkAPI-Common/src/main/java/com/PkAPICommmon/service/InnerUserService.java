package com.PkAPICommmon.service;


import com.PkAPICommmon.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户服务
 *
 * @author yupi
 */
public interface InnerUserService {
    /**
     * 查看是否给用户分配密钥
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);
}
