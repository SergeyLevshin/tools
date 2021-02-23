package com.toolsapp.controller.tool.general;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class GeneralToolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    GeneralToolController controller;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("showListOfTools test")
    void showListOfToolsTest() throws Exception {
        this.mockMvc.perform(get("/tool/show/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/tool/show"))
                .andReturn();
    }

    @Test
    @DisplayName("delete GET test")
    void deleteTest() throws Exception {
        this.mockMvc.perform(get("/tool/delete"))
                .andExpect(status().isOk())
                .andExpect(view().name("/tool/delete"))
                .andReturn();
    }

    @Test
    @DisplayName("delete POST success test")
    void deleteToolTest() throws Exception {
        this.mockMvc
                .perform(post("/tool/delete").param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/tool/show"))
                .andReturn();
    }
}