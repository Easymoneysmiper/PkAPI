package com.yupi.project.model.dto.InterfaceInfo;

import lombok.Data;

import java.io.Serializable;

@Data
public class InvokeInterfaceRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String UserRequestParams;


}
