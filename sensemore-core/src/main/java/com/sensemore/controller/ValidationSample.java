package com.sensemore.controller;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;

public class ValidationSample {
    public static class User {
        @NotNull(message = "Name cannot be null")
        private String name;
    
        public User(String name){
            this.name = name;
        }
        // getters and setters
    }

    public void createUser(User user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ValidationException("Validation failed");
        }
        
        // 继续处理用户创建逻辑
    }

}
