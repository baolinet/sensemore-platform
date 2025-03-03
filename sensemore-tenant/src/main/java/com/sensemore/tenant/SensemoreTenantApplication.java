package com.sensemore.tenant;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoDatabase;
import com.sensemore.tenant.entity.Student;
import com.sensemore.tenant.repository.StudentMapper;
import com.sensemore.tenant.repository.StudentResultHandler;

import com.sensemore.tenant.repository.CommentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SensemoreTenantApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensemoreTenantApplication.class, args);
	}

	@Autowired
    private StudentMapper studentMapper;

	@Autowired
	private CommentRepository commentRepository;

	@Component
	@Order(1)
	@Profile("!prod")
	public class AfterStartRunner implements CommandLineRunner {
	
		public void run(String... args) {
	
			System.out.println("CommandLineRunner is running!");
			// 在这里添加您需要在应用启动后执行的代码

			Student student = studentMapper.getStudentById(1);
			log.info("学生:{}",student);

			StudentResultHandler resultHandler = new StudentResultHandler();
			studentMapper.searchBigData(resultHandler);

			log.info("全部学生：{}",resultHandler.getResults());


			commentRepository.showDatabase();

			// commentRepository.testInsertAll();;
			commentRepository.testFindById();
		}
	}
}
