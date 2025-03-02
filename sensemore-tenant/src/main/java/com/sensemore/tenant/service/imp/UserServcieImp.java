package com.sensemore.tenant.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensemore.tenant.entity.Student;
import com.sensemore.tenant.repository.StudentMapper;
import com.sensemore.tenant.service.UserService;

@Service
public class UserServcieImp implements UserService{
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student findById(Integer id) {
        return studentMapper.getStudentById(id);
    }
}
