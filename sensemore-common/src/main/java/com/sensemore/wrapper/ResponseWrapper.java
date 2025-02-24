package com.sensemore.wrapper;

import lombok.Getter;

@Getter
public class ResponseWrapper<T> {
    private String code;
    private String msg;
    private T data;

    public static <T> ResponseWrapper<T> success(T data) {
        ResponseWrapper<T> result = new ResponseWrapper<>();
        result.data = data;
        result.code = "200";
        result.msg = "成功";
        return result;
    }

    public static <T> ResponseWrapper<T> failure(String reason) {
        ResponseWrapper<T> result = new ResponseWrapper<>();
        result.code = "400";
        result.msg = reason;
        result.data = (T) "";
        return result;
    }

    public static <T> ResponseWrapper<T> error(String error) {
        ResponseWrapper<T> result = new ResponseWrapper<>();
        result.code = "500";
        result.msg = error;
        result.data = (T) "";
        return result;
    }
}
