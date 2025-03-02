package com.sensemore.tenant.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import com.sensemore.tenant.entity.Student;

public class StudentResultHandler implements ResultHandler<Student> {
    private final List<Student> list = new ArrayList<>();

    @Override
    public void handleResult(ResultContext<? extends Student> context) {
        Student record = context.getResultObject();
        list.add(record);
    }
    
    public List<Student> getResults(){
        return list;
    }
}