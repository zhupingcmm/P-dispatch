package com.mf.server.exception;

import com.mf.dispatch.common.base.BaseResponse;
import com.mf.dispatch.common.base.ResponseEnum;
import com.mf.dispatch.common.exception.DispatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResponse<?> exceptionHandler(Exception exception, HttpServletResponse response) {
        response.setStatus(500);
        if (exception instanceof DispatchException) {
            DispatchException dispatchException = (DispatchException) exception;

            ResponseEnum responseEnum = dispatchException.getResponseEnum();

            if (responseEnum == null) {
                return new BaseResponse(dispatchException.getCode(),dispatchException.getMessage());
            } else {
                return new BaseResponse<>(responseEnum);
            }
        } else {
            return BaseResponse.error();
        }
    }
}
