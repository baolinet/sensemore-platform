package com.sensemore.wrapper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletResponse;

//另一种包装响应结果实现方式
// @Aspect
// @Component
public class ResponseWrapperAspect {

    @Around("@annotation(org.springframework.web.bind.annotation.*)")
    public Object autoReturn(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Object result = joinPoint.proceed();
        // result转json再写入response
        return ResponseWrapper.success(result);
    }
}
