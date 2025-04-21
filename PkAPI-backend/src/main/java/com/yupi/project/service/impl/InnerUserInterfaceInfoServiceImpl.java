package com.yupi.project.service.impl;

import com.PkAPICommmon.model.entity.UserInterfaceInfo;
import com.PkAPICommmon.service.InnerUserInterfaceInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.project.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    private static final Logger log = LoggerFactory.getLogger(InnerUserInterfaceInfoServiceImpl.class);
    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;
    @Override
    public void InvokeInterfaceCount(Long userId, Long interfaceInfoId) {
        userInterfaceInfoService.InvokeInterfaceCount(userId, interfaceInfoId);
    }

    @Override
    public Boolean CheckUserInterfaceInfo(Long userId, Long interfaceInfoId) {
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userId);
        queryWrapper.eq("interfaceInfoId",interfaceInfoId);
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoService.getOne(queryWrapper);
        if (userInterfaceInfo == null) {
            log.info("未给该用户：{} 分配该接口：{}调用次数", userId, interfaceInfoId);
            return false;
        }
        if (userInterfaceInfo.getLeftNum()<=0) {
            log.info("该用户：{} d1该接口：{}调用次数不足", userId, interfaceInfoId);
            return false;
        }
        return true;
    }
}
