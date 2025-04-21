package com.yupi.project.service;

import com.PkAPICommmon.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author easymoneysniper
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2025-04-13 16:34:41
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    /**
     * 校验用户接口信息参数
     * @param interfaceInfo
     * @param b
     */
    void validUserInterfaceInfo(UserInterfaceInfo interfaceInfo, boolean b);

    /**
     * 调用接口次数的管理方法
     * @param userId
     * @param interfaceInfoId
     */
    void InvokeInterfaceCount(Long userId,Long interfaceInfoId);

}
