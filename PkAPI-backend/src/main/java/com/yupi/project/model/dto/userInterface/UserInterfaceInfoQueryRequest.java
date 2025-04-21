package com.yupi.project.model.dto.userInterface;

import com.yupi.project.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInterfaceInfoQueryRequest extends PageRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 调用用户id
     */
    private Long userId;

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


}
