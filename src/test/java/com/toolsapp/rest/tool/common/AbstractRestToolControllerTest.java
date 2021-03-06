package com.toolsapp.rest.tool.common;

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

import static com.toolsapp.testutils.TestUtil.asJsonString;
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

     protected E getNewTool() {
        E tool = createTool();
        tool.setId(counterId);
        tool.setName("name" + counterId);
        counterId++;
        return tool;
    }

    @AfterEach
    void resetCounter() {
        counterId = 1;
    }

    @Test
    @DisplayName("findAll success test")
    void findAllTest() throws Exception {
        E tool1 = this.getNewTool();
        E tool2 = this.getNewTool();

        when(service.findAllSortByName()).thenReturn(Arrays.asList(tool1, tool2));

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("name1"))
                .andExpect(jsonPath("$[1].id").value(2))
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
        E tool = this.getNewTool();

        when(service.findById(1L)).thenReturn(Optional.of(tool));

        mockMvc.perform(MockMvcRequestBuilders.get(uri + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("name1"));
        verify(service, times(1)).findById(1);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("findById not found test")
    void findByIdNotFoundTest() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get(uri + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(service, times(1)).findById(1);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("save success test")
    void save() throws Exception {
        E tool = this.getNewTool();

        when(service.save(tool)).thenReturn(tool);

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(tool)))
                .andExpect(status().isCreated());
        verify(service, times(1)).save(any());
        verifyNoMoreInteractions(service);
    }
}