package com.yupi.project.model.dto.InterfaceInfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 创建请求
 *
 * @TableName product
 */
@Data
public class InterfaceInfoAddRequest implements Serializable {


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
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;

    private static final long serialVersionUID = 1L;
}