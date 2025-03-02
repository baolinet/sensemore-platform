package com.sensemore.controller;

import org.springframework.web.bind.annotation.RestController;
import com.sensemore.dto.UserDto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Core";
    }

    @GetMapping("/hello/{param}")
    public String getMethodName(@PathVariable("param") String param) {
        return "hello:" + param;
    }

    @GetMapping("/users")
    public String getUserInfo(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "age", required = false) Integer age) {
        return "Name: " + name + ", Age: " + age;
    }

    @PostMapping("/user/add")
    public @ResponseBody UserDto postUserInfo(@RequestParam(value = "name") String name) {
        // User user =  new ValidationSample.User(name);
        // return user;
        System.out.println("post user/add");
        return new UserDto("lisi");
    }

        //参数校验器
    // @Autowired
    // private Validator validator;

    
    @PostMapping("/user/delete")
    public @ResponseBody String deleteUser(@Valid @RequestBody UserDto userDto){

        // Set<ConstraintViolation<UserDto>> violationSet = validator.validate(userDto);
        // violationSet.forEach(violation -> {
        //     //name不能为空
        //     //age最小不能小于18
        //     System.out.println(violation.getPropertyPath() + violation.getMessage());
        // });

        return "";
    }
}
