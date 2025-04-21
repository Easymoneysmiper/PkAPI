package com.yupi.project.service;

import com.PkAPICommmon.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author easymoneysniper
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2025-04-08 20:43:46
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean b);
}
