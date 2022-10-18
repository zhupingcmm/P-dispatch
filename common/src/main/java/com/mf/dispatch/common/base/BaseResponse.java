package com.mf.dispatch.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> extends BaseBean{

    private int code;

    private String message;

    private T data;

    public BaseResponse(ResponseEnum responseEnum){
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public BaseResponse(int code, String message){
        this.message = message;
        this.code = code;
    }

    public static <T> BaseResponse<T> success (T data){
        BaseResponse<T> response = new BaseResponse<>(ResponseEnum.SUCCESS);
        response.setData(data);
        return response;
    }


    public static <T> BaseResponse<T> error () {
        return new BaseResponse<>(ResponseEnum.SYSTEM_ERROR);
    }



}
