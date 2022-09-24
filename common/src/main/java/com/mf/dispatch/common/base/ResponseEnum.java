package com.mf.dispatch.common.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ResponseEnum {

    SEND_DATA_ERROR(1, "send message error"),
    NO_ROWS_AFFECTED(2, "failed update"),
    TOO_MANY_ROWS_AFFECTED(3, "have affect other data"),
    NOT_TOTAL_ROWS_AFFECTED(4, "data is missing"),

    FAILED_TO_TRANSFORM(5, "failed to transform"),
    SUCCESS(6, "request success");

    @Getter
    private int code;

    @Getter
    private String message;


}
