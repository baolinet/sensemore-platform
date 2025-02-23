package com.sensemore.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


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
}
