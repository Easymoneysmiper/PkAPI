package com.PkAPICommmon.service;


import com.PkAPICommmon.model.entity.UserInterfaceInfo;

/**
* @author easymoneysniper
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2025-04-13 16:34:41
*/
public interface InnerUserInterfaceInfoService {



    /**
     * 调用接口次数的管理方法
     * @param userId
     * @param interfaceInfoId
     */
    void InvokeInterfaceCount(Long userId,Long interfaceInfoId);

    /**
     * 查看是否分配次数或者是否还有次数
     * @param userId
     * @param interfaceInfoId
     * @return
     */
    Boolean CheckUserInterfaceInfo(Long userId,Long interfaceInfoId);
}
