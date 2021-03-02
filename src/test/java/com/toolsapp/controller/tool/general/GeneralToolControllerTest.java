package com.toolsapp.controller.tool.general;

import com.toolsapp.service.tools.common.GeneralToolService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GeneralToolController.class)
@MockBean(GeneralToolService.class)
class GeneralToolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("showListOfTools test")
    void showListOfToolsTest() throws Exception {
        this.mockMvc.perform(get("/tool/show/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("/tool/show"))
                .andReturn();
    }

    @Test
    @DisplayName("delete GET test")
    void deleteTest() throws Exception {
        this.mockMvc.perform(get("/tool/delete"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("/tool/delete"))
                .andReturn();
    }


    @Test
    @DisplayName("delete POST failed test")
    void deleteToolWithNoIdTest() throws Exception {
        this.mockMvc
                .perform(post("/tool/delete"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
}