package com.toolsapp.rest.tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toolsapp.domain.tools.SupportTool;
import com.toolsapp.rest.tool.common.RestSupportToolController;
import com.toolsapp.service.tools.common.SupportToolService;
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
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RestSupportToolController.class)
public class RestSupportToolControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SupportToolService service;

    private final String uri = "/rest/tool/support/";

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("findAll success test")
    void findAllTest() throws Exception {
        SupportTool tool1 = new SupportTool();
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
    @DisplayName("findById success test")
    void findByIdTest() throws Exception {
        SupportTool tool = new SupportTool();
        tool.setId(1L);
        tool.setName("name");

        when(service.findById(1L)).thenReturn(Optional.of(tool));

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("name"));
        verify(service, times(1)).findById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("findById not found test")
    void findByIdNotFoundTest() throws Exception {

        when(service.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(service, times(1)).findById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("save success test")
    void save() throws Exception {
        SupportTool tool = new SupportTool();
        tool.setId(1L);
        tool.setName("name");

        when(service.save(tool)).thenReturn(tool);

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(tool)))
                .andExpect(status().isCreated());
        verify(service, times(1)).save(any());
        verifyNoMoreInteractions(service);
    }
}
