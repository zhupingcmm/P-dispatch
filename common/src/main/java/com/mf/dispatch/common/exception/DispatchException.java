package com.mf.dispatch.common.exception;

import com.mf.dispatch.common.base.ResponseEnum;
import lombok.Getter;

@Getter
public class DispatchException extends RuntimeException{
    /**
     * error code
     */
    private int code;
    /**
     * error message
     */
    private String message;

    private ResponseEnum responseEnum;

    public DispatchException(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }
}
