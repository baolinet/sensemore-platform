package com.sensemore.tenant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sensemore.tenant.dto.UserDto;

import reactor.core.publisher.Mono;

@RestController
public class UserController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, WebFlux !";
    }

    @GetMapping("/user")
    public Mono<UserDto> getUser() {
        UserDto user = new UserDto();
        user.setName("犬小哈");
        user.setDesc("欢迎关注我的公众号: 小哈学Java");
        return Mono.just(user);
    }
}


