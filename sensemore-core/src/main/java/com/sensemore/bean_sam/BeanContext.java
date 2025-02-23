package com.sensemore.bean_sam;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class BeanContext implements ApplicationContextAware{
    
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void printBeans() {
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    public String testBeanFunc(){
        TestBean test = (TestBean) context.getBean("testBean");
        return test.test("北京");
    }

}
