package com.sensemore.aspect_sam;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile({"prod"})
public class LoggingAspect {
 
    // 前置通知：在方法执行之前执行
    @Before("execution(* com.sensemore.cache_sam.CacheService.findBookByIsbn(..))")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs(); // 获取方法参数
        System.out.println("Before executing findBookByIsbn(), ISBN: " + args[0]);
    }

    // 后置通知：在方法正常返回后执行
    @AfterReturning(pointcut = "execution(* com.sensemore.cache_sam.CacheService.findBookByIsbn(..))", returning = "result")
    public void logAfterReturning(Object result) {
        System.out.println("After executing findBookByIsbn(), result: " + result);
    }

    // 异常通知：在方法抛出异常时执行
    @AfterThrowing(pointcut = "execution(* com.sensemore.cache_sam.CacheService.findBookByIsbn(..))", throwing = "ex")
    public void logAfterThrowing(Throwable ex) {
        System.out.println("Exception occurred in findBookByIsbn(): " + ex.getMessage());
    }

    // 最终通知：无论方法是否抛出异常，都会执行
    @After("execution(* com.sensemore.cache_sam.CacheService.findBookByIsbn(..))")
    public void logAfter() {
        System.out.println("findBookByIsbn() execution completed.");
    }
}
