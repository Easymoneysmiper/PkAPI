package com.yupi.project.model.dto.userInterface;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新请求
 *
 * @TableName product
 */
@Data
public class UserInterfaceInfoUpdateRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 接口id
     */
    private Long interfaceInfoId;
    /**
     * 总调用次数
     */
    private Integer totalNum;

    /**
     * 0-正常，1-禁用
     */
    private Integer status;
    /**
     * 是否删除(0-未删, 1-已删)
     */
    private Integer isDelete;


    private static final long serialVersionUID = 1L;
}