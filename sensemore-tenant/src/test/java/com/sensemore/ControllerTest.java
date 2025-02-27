package com.sensemore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sensemore.controller.UserController;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest(UserController.class)
// @SpringBootTest
// @AutoConfigureMockMvc

@WebMvcTest // 或者使用 @SpringBootTest 配合 @AutoConfigureMockMvc，取决于你的需求和测试范围大小
@EnableWebMvc // 如果需要额外的Web配置，可以启用此注解来覆盖默认配置
public class ControllerTest {
    
   @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); // 手动设置MockMvc实例
    }

    @Autowired
    private UserController userController;

    @Test
    public void testGetEndpoint() throws Exception {
        System.out.println("hello web mvc");
        String str = userController.hello();
        System.out.println(str);
    //     mockMvc.perform(get("/hello"))
    //            .andExpect(status().isOk());
    }
}
