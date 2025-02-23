package com.sensemore.bean_sam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Arrays;
 
@Component
public class BeanCommandRunner implements CommandLineRunner {//ApplicationRunner
    @Autowired
    private ApplicationContext context;
    
    @Override
    public void run(String... args) throws Exception {
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            // System.out.println(beanName);
        }
    }
}