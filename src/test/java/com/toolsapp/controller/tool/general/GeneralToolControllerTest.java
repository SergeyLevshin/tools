package com.toolsapp.controller.tool.general;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.repository.tools.AbstractToolRepository;
import com.toolsapp.repository.tools.CuttingToolsRepository;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
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