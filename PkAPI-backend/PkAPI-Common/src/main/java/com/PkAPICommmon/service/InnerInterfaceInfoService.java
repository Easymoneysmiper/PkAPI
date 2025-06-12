package com.PkAPICommmon.service;


import com.PkAPICommmon.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author easymoneysniper
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2025-04-08 20:43:46
*/
public interface InnerInterfaceInfoService  {
    /**
     * 查看接口是否存在
     * @param path
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String path,String method);
}
