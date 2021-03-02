package com.toolsapp.rest.property;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toolsapp.domain.property.Producer;
import com.toolsapp.domain.property.ToolFunction;
import com.toolsapp.domain.property.ToolProperty;
import com.toolsapp.service.property.extended.ExtendedPropertyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.tools.Tool;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestPropertyController.class)
class RestPropertyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ExtendedPropertyService service;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("getAllToolProperties success test")
    void getAllToolPropertiesTest() throws Exception {
        List<ToolProperty> properties = new ArrayList<>();
        properties.add(new Producer());
        properties.add(new ToolFunction());

        when(service.findAllToolProperties())
                .thenReturn(properties);

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/property/allToolProperties")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        verify(service, times(1)).findAllToolProperties();
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("getAllToolProperties failed test")
    void getAllToolPropertiesFailedTest() throws Exception {

        when(service.findAllToolProperties())
                .thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/property/allToolProperties")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(service, times(1)).findAllToolProperties();
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("getSingleProducer success test")
    void getSingleProducerTest() throws Exception {
        Producer producer = new Producer();
        producer.setId(1L);
        producer.setName("name");

        when(service.findProducerById(1L)).thenReturn(Optional.of(producer));

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/property/producer/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("name"));
        verify(service, times(1)).findProducerById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("getSingleProducer not found test")
    void getSingleProducerNotFoundTest() throws Exception {
        when(service.findProducerById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/property/producer/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(service, times(1)).findProducerById(1L);
        verifyNoMoreInteractions(service);
    }


    @Test
    @DisplayName("getSingleToolFunction success test")
    void getSingleToolFunctionTest() throws Exception {
        ToolFunction toolFunction = new ToolFunction();
        toolFunction.setId(1L);
        toolFunction.setName("name");

        when(service.findToolFunctionById(1L)).thenReturn(Optional.of(toolFunction));

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/property/toolFunction/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("name"));
        verify(service, times(1)).findToolFunctionById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("getSingleToolFunction not found test")
    void getSingleToolFunctionNotFoundTest() throws Exception {
        when(service.findToolFunctionById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/property/toolFunction/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(service, times(1)).findToolFunctionById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("createProducer success test")
    void createProducerTest() throws Exception {
        Producer producer = new Producer();
        producer.setId(1L);
        producer.setName("name");

        when(service.saveProducer(producer)).thenReturn(producer);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/property/addProducer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(producer)))
                .andExpect(status().isCreated());
        verify(service, times(1)).saveProducer(producer);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("createToolFunction success test")
    void createToolFunction() throws Exception {
        ToolFunction toolFunction = new ToolFunction();
        toolFunction.setId(1L);
        toolFunction.setName("name");

        when(service.saveToolFunction(toolFunction)).thenReturn(toolFunction);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/property/addToolFunction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(toolFunction)))
                .andExpect(status().isCreated());
        verify(service, times(1)).saveToolFunction(toolFunction);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("deleteProduct success test")
    void deleteProducerTest() throws Exception {
        when(service.deleteProducerById(1L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/property/deleteProducer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteProducerById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("deleteProduct failed test")
    void deleteProducerFailedTest() throws Exception {
        when(service.deleteProducerById(1L)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/property/deleteProducer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
        verify(service, times(1)).deleteProducerById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("deleteToolFunction success test")
    void deleteToolFunctionTest() throws Exception {
        when(service.deleteToolFunctionById(1L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/property/deleteToolFunction/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteToolFunctionById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("deleteToolFunction failed test")
    void deleteToolFunctionFailedTest() throws Exception {
        when(service.deleteToolFunctionById(1L)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/property/deleteToolFunction/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
        verify(service, times(1)).deleteToolFunctionById(1L);
        verifyNoMoreInteractions(service);
    }

}