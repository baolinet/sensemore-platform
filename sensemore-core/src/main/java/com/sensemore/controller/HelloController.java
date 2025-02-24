package com.sensemore.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sensemore.controller.ValidationSample.User;
import com.sensemore.dto.UserDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class HelloController {

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
}
