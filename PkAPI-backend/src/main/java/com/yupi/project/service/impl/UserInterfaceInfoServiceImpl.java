package com.yupi.project.service.impl;





import com.PkAPICommmon.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yupi.project.common.ErrorCode;
import com.yupi.project.exception.BusinessException;
import com.yupi.project.mapper.UserInterfaceInfoMapper;
import com.yupi.project.service.UserInterfaceInfoService;
import org.springframework.stereotype.Service;

/**
* @author easymoneysniper
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2025-04-13 16:34:41
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {


    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new IllegalArgumentException("用户接口信息不能为空");
        }

        // 新增操作的校验
        if (add) {
            // 校验必填字段
            if (userInterfaceInfo.getUserId() == null || userInterfaceInfo.getUserId() <= 0) {
                throw new IllegalArgumentException("用户ID必须为正数");
            }
            if (userInterfaceInfo.getInterfaceInfoId() == null || userInterfaceInfo.getInterfaceInfoId() <= 0) {
                throw new IllegalArgumentException("接口ID必须为正数");
            }
            if (userInterfaceInfo.getTotalNum() == null || userInterfaceInfo.getTotalNum() < 0) {
                throw new IllegalArgumentException("总调用次数不能为空且必须非负");
            }
            if (userInterfaceInfo.getLeftNum() == null || userInterfaceInfo.getLeftNum() < 0) {
                throw new IllegalArgumentException("剩余调用次数不能为空且必须非负");
            }
            if (userInterfaceInfo.getStatus() == null) {
                throw new IllegalArgumentException("状态不能为空");
            }

            // 防止手动设置自动生成字段
            if (userInterfaceInfo.getCreateTime() != null) {
                throw new IllegalArgumentException("创建时间由系统自动生成，无需传入");
            }
            if (userInterfaceInfo.getUpdateTime() != null) {
                throw new IllegalArgumentException("更新时间由系统自动生成，无需传入");
            }
        }

        // 通用校验（新增/更新均需校验）
        if (userInterfaceInfo.getUserId() != null && userInterfaceInfo.getUserId() <= 0) {
            throw new IllegalArgumentException("用户ID必须为正数");
        }
        if (userInterfaceInfo.getInterfaceInfoId() != null && userInterfaceInfo.getInterfaceInfoId() <= 0) {
            throw new IllegalArgumentException("接口ID必须为正数");
        }
        if (userInterfaceInfo.getTotalNum() != null && userInterfaceInfo.getTotalNum() < 0) {
            throw new IllegalArgumentException("总调用次数不能为负数");
        }
        if (userInterfaceInfo.getLeftNum() != null && userInterfaceInfo.getLeftNum() < 0) {
            throw new IllegalArgumentException("剩余调用次数不能为负数");
        }
        if (userInterfaceInfo.getStatus() != null && userInterfaceInfo.getStatus() != 0 && userInterfaceInfo.getStatus() != 1) {
            throw new IllegalArgumentException("状态必须为0（正常）或1（禁用）");
        }
        if (userInterfaceInfo.getIsDelete() != null) {
            throw new IllegalArgumentException("是否删除标记由系统维护，禁止手动修改");
        }

        // 更新时防止修改创建时间
        if (!add && userInterfaceInfo.getCreateTime() != null) {
            throw new IllegalArgumentException("创建时间不可修改");
        }
    }
    @Override
    public void InvokeInterfaceCount(Long userId, Long interfaceInfoId) {
        if (userId == null || interfaceInfoId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        if(userId<=0 || interfaceInfoId<=0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userId", userId);
        updateWrapper.eq("interfaceInfoId ", interfaceInfoId);
        updateWrapper.setSql("leftNum =leftNum-1,totalNum=totalNum+1");
        this.update(updateWrapper);
    }

}




