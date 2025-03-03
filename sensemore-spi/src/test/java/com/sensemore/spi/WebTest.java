package com.sensemore.spi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureMockMvc
public class WebTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHello() throws Exception{
        mockMvc.perform(get("/hello"))
        .andExpect(status().isOk());
    }
}

