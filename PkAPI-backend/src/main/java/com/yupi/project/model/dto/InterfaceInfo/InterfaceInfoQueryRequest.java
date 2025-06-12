package com.yupi.project.model.dto.InterfaceInfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yupi.project.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class InterfaceInfoQueryRequest extends PageRequest implements Serializable {
    private static final long serialVersionUID = 1L;
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
}
