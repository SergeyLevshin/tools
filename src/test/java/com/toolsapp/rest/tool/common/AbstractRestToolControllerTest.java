package com.toolsapp.rest.tool.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.service.CommonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

abstract class AbstractRestToolControllerTest<E extends AbstractTool,
        S extends CommonService<E>> {

    public AbstractRestToolControllerTest(String uri) {
        this.uri = uri;
    }

    @Autowired
    MockMvc mockMvc;

    @MockBean
    S service;

    private final String uri;

    static long counterId = 1;

    protected abstract E createTool();

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void resetCounter() {
        counterId = 1;
    }

    @Test
    @DisplayName("findAll success test")
    void findAllTest() throws Exception {
        E tool1 = this.createTool();
        E tool2 = this.createTool();

        when(service.findAllSortByName()).thenReturn(Arrays.asList(tool1, tool2));

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(counterId))
                .andExpect(jsonPath("$[0].name").value("name" + counterId))
                .andExpect(jsonPath("$[1].id").value(counterId))
                .andExpect(jsonPath("$[1].name").value("name" + counterId));
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
        E tool = this.createTool();

        when(service.findById(1L)).thenReturn(Optional.of(tool));

        mockMvc.perform(MockMvcRequestBuilders.get(uri + counterId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(counterId))
                .andExpect(jsonPath("name").value("name" + counterId));
        verify(service, times(1)).findById(counterId);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("findById not found test")
    void findByIdNotFoundTest() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get(uri + counterId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(service, times(1)).findById(counterId);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("save success test")
    void save() throws Exception {
        E tool = this.createTool();

        when(service.save(tool)).thenReturn(tool);

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(tool)))
                .andExpect(status().isCreated());
        verify(service, times(1)).save(any());
        verifyNoMoreInteractions(service);
    }
}