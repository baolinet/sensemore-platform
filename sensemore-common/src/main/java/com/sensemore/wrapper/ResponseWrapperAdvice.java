package com.sensemore.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.sensemore.converter.JsonConverter;

@ControllerAdvice
public class ResponseWrapperAdvice implements ResponseBodyAdvice<Object> {

    private static final MediaType JSON_MEDIA_TYPE = MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE);

    @Override
    @Nullable
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter,
            MediaType mediaType,
            Class<? extends HttpMessageConverter<?>> aClass,
            ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse) {
        if (obj == null) {
            return ResponseWrapper.success("");
        }

        Object result = ResponseWrapper.success(obj);

        if (aClass == StringHttpMessageConverter.class) {
            serverHttpResponse.getHeaders().setContentType(JSON_MEDIA_TYPE);
            result = JsonConverter.toJson(result);
        }

        return result;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 全部都走这个Advice，也可以自己过滤
        return true;
    }
}
