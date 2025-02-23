package com.sensemore.bean_sam;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class TestBean implements InitializingBean, DisposableBean{
    public String test(String input){
        return input + "：OK";
    }

    @PostConstruct
    public void init() {
        System.out.println("TestBean initialized using @PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestBean initialized using InitializingBean interface");
    }

    @Override
    public void destroy() throws Exception {
        // 释放资源的代码
        System.out.println("TestBean Resource released during Spring container shutdown");
    }

    @PreDestroy
    public void cleanup() {
        // 释放资源的代码
        System.out.println("TestBean Resource released using @PreDestroy");
    }
}
