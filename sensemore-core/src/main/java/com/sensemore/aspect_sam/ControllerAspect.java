package com.sensemore.aspect_sam;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
@Profile({"dev", "test"})
public class ControllerAspect {

    private static final  Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restControllerMethods() {
        // 这是一个空方法，仅作为切点表达式使用
    }

    // 使用@Around注解定义环绕通知
    @Around("restControllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取Request请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 在方法执行前打印日志
        logger.info("Executing method: {}", joinPoint.getSignature().toShortString());
        // 记录方法执行开始时间
        long start = System.currentTimeMillis();
        // 执行被拦截的方法
        Object result = joinPoint.proceed();
        // 记录方法执行结束时间，并计算耗时
        long executionTime = System.currentTimeMillis() - start;
        logger.info("Method executed in {}ms: {}", executionTime, joinPoint.getSignature().toShortString());
        logger.info("Method param:{}", joinPoint.getArgs());
        logger.info("Method result:{}", result);
        // 返回方法执行结果
        return result;
    }
}
