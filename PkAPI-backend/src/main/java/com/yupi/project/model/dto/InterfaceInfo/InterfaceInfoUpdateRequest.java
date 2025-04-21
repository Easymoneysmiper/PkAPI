package com.yupi.project.model.dto.InterfaceInfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 更新请求
 *
 * @TableName product
 */
@Data
public class InterfaceInfoUpdateRequest implements Serializable {

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
     * 是否删除(0-未删, 1-已删)
     */
    @TableLogic
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}