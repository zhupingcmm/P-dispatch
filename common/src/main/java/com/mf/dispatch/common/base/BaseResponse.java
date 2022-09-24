package com.mf.dispatch.common.base;

import lombok.Data;

@Data
public class BaseResponse<T> extends BaseBean{

    private int code;

    private String message;

    private T data;

    public BaseResponse(ResponseEnum responseEnum){
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public static <T> BaseResponse<T> success (T data){
        BaseResponse<T> response = new BaseResponse<>(ResponseEnum.SUCCESS);
        response.setData(data);
        return response;
    }



}
