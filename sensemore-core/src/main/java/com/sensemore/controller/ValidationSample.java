package com.sensemore.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

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
