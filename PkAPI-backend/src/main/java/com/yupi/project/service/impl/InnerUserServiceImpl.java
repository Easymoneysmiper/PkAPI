package com.yupi.project.service.impl;


import com.PkAPICommmon.model.entity.User;
import com.PkAPICommmon.service.InnerUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.project.common.ErrorCode;
import com.yupi.project.exception.BusinessException;
import com.yupi.project.mapper.UserMapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User getInvokeUser(String accessKey) {
            if (StringUtils.isAnyBlank(accessKey)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("accessKey",accessKey);
            return userMapper.selectOne(queryWrapper);

    }
}
