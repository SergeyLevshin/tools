package com.toolsapp.rest.tool;

import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.domain.tools.SupportTool;
import com.toolsapp.rest.tool.common.RestGeneralToolController;
import com.toolsapp.service.tools.common.GeneralToolService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestGeneralToolController.class)
public class RestGeneralToolControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GeneralToolService service;

    private final String uri = "/rest/tool/general";

    @Test
    @DisplayName("findAll success test")
    void findAllTest() throws Exception {
        CuttingTool tool1 = new CuttingTool();
        tool1.setId(1L);
        tool1.setName("name1");
        SupportTool tool2 = new SupportTool();
        tool2.setId(2L);
        tool2.setName("name2");

        when(service.findAllSortByName()).thenReturn(Arrays.asList(tool1, tool2));

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("name1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("name2"));
        verify(service, times(1)).findAllSortByName();
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("findAll not found test")
    void findAllNotFoundTest() throws Exception {
        when(service.findAllSortByName()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(service, times(1)).findAllSortByName();
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("deleteById success test")
    void deleteByIdTest() throws Exception {
        when(service.deleteTool(1L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete(uri +"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteTool(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("deleteProduct failed test")
    void deleteProductFailedTest() throws Exception {
        when(service.deleteTool(1L)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete(uri +"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
        verify(service, times(1)).deleteTool(1L);
        verifyNoMoreInteractions(service);
    }
}
