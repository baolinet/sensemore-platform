package com.sensemore.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;

import com.sensemore.entity.Student;

@Mapper
public interface StudentMapper {
    // 根据id查询student的方法
    public Student getStudentById(@Param("id") Integer id);

    @Select("select * from student")
    @ResultType(Student.class)
    @Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = 100)
    public void searchBigData(ResultHandler<Student> handler);
}
