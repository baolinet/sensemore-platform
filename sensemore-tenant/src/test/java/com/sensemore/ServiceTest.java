package com.sensemore;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sensemore.service.UserService;

@SpringBootTest
public class ServiceTest {
    
    @Autowired
    private UserService userService;

    @Test
    void testService() {
        assertNotNull(userService);
        // 添加更多断言来测试服务逻辑
    }
}
