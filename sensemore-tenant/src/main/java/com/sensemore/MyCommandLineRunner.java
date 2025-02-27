package com.sensemore;

import org.apache.ibatis.session.ResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sensemore.entity.Student;
import com.sensemore.repository.StudentMapper;
import com.sensemore.repository.StudentResultHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner is running!");
        // 在这里添加您需要在应用启动后执行的代码

        Student student = studentMapper.getStudentById(1);
        log.info("学生:{}",student);

        StudentResultHandler resultHandler = new StudentResultHandler();
        studentMapper.searchBigData(resultHandler);

        log.info("全部学生：{}",resultHandler.getResults());
    }
}