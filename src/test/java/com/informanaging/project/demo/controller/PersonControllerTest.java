package com.informanaging.project.demo.controller;

import com.informanaging.project.demo.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
public class PersonControllerTest {

    @Autowired
    private PersonController personController;
    @Autowired
    private PersonRepository personRepository;

    private MockMvc mockMvc;

    // テスト実施する前に必ず実施する！
    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    void getPerson() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void postPerson() throws Exception {
        mockMvc.perform(
//                MockMvcRequestBuilders.post("/api/person?name=martin&age=20&bloodType=A")
                MockMvcRequestBuilders.post("/api/person")
                       .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": \"martin2\", \n" +
                        "    \"age\": 20, \n" +
                        "    \"bloodType\": \"A\"\n" +
                        "}"))
                        .andDo(print())
//                        .andExpect(status().isCreated());
                        .andExpect(status().isOk());
    }

    @Test
    void modifyPerson() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\n" +
                                "    \"name\": \"martin\", \n" +
                                "    \"age\": 21, \n" +
                                "    \"bloodType\": \"A\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void modifyName() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                .param("name", "martin22"))
                .andDo(print())
               .andExpect(status().isOk());
    }

    @Test
    void deletePerson() throws Exception {
        // 이 처럼 데이터를 완전 삭제하는 것이 아닌, 플래그를 사용해서 소프트 DELETE하는 것이 주류적인 방법이다
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());

        log.info("people deleted : {}", personRepository.findPeopleDeleted());
    }
}
