package com.informanaging.project.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloWorldControllerTest {
    @Autowired // Spring contextからBeanをinject
    private HelloWorldController helloWorldController;

    private MockMvc mockMvc;

    @Test
    public void helloWorld() {
        assertThat(helloWorldController.helloWorld()).isEqualTo("HelloWorld");
    }

    @Test
    public void mockMvcTest() throws Exception {
        // mockMvc setting
        mockMvc = MockMvcBuilders.standaloneSetup(helloWorldController).build();

        // mockMvcを動かさせる
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/helloWorld")
                ).andDo(MockMvcResultHandlers.print()) // 詳細確認
                .andExpect(MockMvcResultMatchers.status().isOk()) // 200かを確認
                .andExpect(MockMvcResultMatchers.content().string("helloWorld")); // 内容の確認
    }
}