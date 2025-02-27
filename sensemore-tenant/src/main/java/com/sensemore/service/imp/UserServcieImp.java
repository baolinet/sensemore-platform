package com.sensemore.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensemore.entity.Student;
import com.sensemore.repository.StudentMapper;
import com.sensemore.service.UserService;

@Service
public class UserServcieImp implements UserService{
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student findById(Integer id) {
        return studentMapper.getStudentById(id);
    }
}
