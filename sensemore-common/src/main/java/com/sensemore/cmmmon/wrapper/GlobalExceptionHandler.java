package com.sensemore.cmmmon.wrapper;

import java.net.BindException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;

@RestControllerAdvice // 捕获所有 @RestController 中的异常
public class GlobalExceptionHandler {

    /* @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        return new ResponseEntity<>("Error: request parameter error", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithmeticException(ArithmeticException ex) {
        return new ResponseEntity<>("Error: Division by zero is not allowed!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder message = new StringBuilder("Validation errors: ");
        ex.getConstraintViolations().forEach(violation -> message.append(violation.getPropertyPath()).append(": ")
                .append(violation.getMessage()).append("; "));
        return new ResponseEntity<>(message.toString(), HttpStatus.BAD_REQUEST);
    } */

    /**
     * 处理 @RequestBody参数校验异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseWrapper<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> ((FieldError) objectError).getField()
                        + ((FieldError) objectError).getDefaultMessage())
                .collect(Collectors.joining(","));
        // "errorMsg": "name不能为空,age最小不能小于18"
        return ResponseWrapper.failure(errorMsg);
    }

    // 捕获其他未处理的异常
    @ExceptionHandler(Exception.class)
    public ResponseWrapper<String> handleException(Exception ex) {
        return ResponseWrapper.error("Internal server error: " + ex.getMessage());
    }
}
