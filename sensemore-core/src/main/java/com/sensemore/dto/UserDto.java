package com.sensemore.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    
    @NotNull
    @Size(min=5,max=10)
    private String name;

    public UserDto(){

    }

    public UserDto(String name){
        this.name = name;
    }
}
