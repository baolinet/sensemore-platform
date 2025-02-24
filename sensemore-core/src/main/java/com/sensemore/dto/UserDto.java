package com.sensemore.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;

    public UserDto(String name){
        this.name = name;
    }
}
