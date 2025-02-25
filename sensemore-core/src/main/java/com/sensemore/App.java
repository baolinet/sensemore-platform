package com.sensemore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

import com.sensemore.bean_sam.*;
import com.sensemore.cache_sam.CacheService;
import com.sensemore.event_sam.MessageEvent;
import com.sensemore.excel_sam.ExcelFillUtil;
import com.sensemore.properties_sam.AppConfig;
import com.sensemore.retry_sam.RetryInvoker;

/**
 * Hello world!
 *
 */
@EnableCaching
@SpringBootApplication()
public class App 
{
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	private BeanContext beanContext;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private RetryInvoker retryInvoker;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private CacheService cacheService;
	
	@Bean
	// @Profile("!prod")
	CommandLineRunner runner() {
		return args -> {
			//测试代码获取bean
			System.out.println(beanContext.testBeanFunc());

			//测试spring事件功能
			//用户注册成功，发布事件
			applicationEventPublisher.publishEvent(new MessageEvent(this, "zhangsan","h"));

			System.out.println(retryInvoker.processFile());;
			

			System.out.println(appConfig.getAppName());


			cacheService.findBookByIsbn("abcd");
			cacheService.findBookByIsbn("abcd");

			ExcelFillUtil.readExcel();
			ExcelFillUtil.templateFill();
		};
	}
}
