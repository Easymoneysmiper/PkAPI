package com.PkAPICommmon.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 接口信息
 * @TableName interface_info
 */
@TableName(value ="interface_info")
@Data
public class InterfaceInfo implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 创建用户
     */
    private Long userId;

    /**
     * 接口路由
     */
    private String url;

    /**
     * 接口描述
     */
    private String description;

    /**
     * 接口方法
     */
    private String method;

    /**
     * 接口状态：（0-开放 1-关闭）
     */
    private Integer status;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除(0-未删, 1-已删)
     */
    @TableLogic
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}