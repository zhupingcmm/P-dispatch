package com.mf.dispatch.common.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ResponseEnum {

    SEND_DATA_ERROR(1, "send message error");

    @Getter
    private int code;

    @Getter
    private String message;


}
