package com.toolsapp.rest.extra;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.service.extra.worker.WorkerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestWorkerController.class)
class RestWorkerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WorkerService service;

    @Test
    @DisplayName("getAllWorkers not empty list  test")
    void getAllProductsTest() throws Exception{
        Worker worker1 = new Worker();
        worker1.setId(1L);
        Worker worker2 = new Worker();
        worker2.setId(2L);
        List<Worker> workers = Arrays.asList(worker1, worker2);

        when(service.findAllSortByName()).thenReturn(workers);

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/worker/workers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
        verify(service, times(1)).findAllSortByName();
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("getAllWorkers empty list test")
    void getAllWorkersEmptyTest() throws Exception{
        when(service.findAllSortByName()).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/worker/workers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(service, times(1)).findAllSortByName();
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("getSingleWorker success test")
    void getSingleProductTest() throws Exception {
        Worker worker = new Worker();
        worker.setId(1L);

        when(service.findById(1L)).thenReturn(Optional.of(worker));

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/worker/workers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("tools").value(new HashMap<>()));
        verify(service, times(1)).findById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("getSingleWorker not found test")
    void getSingleProductNotFoundTest() throws Exception {

        when(service.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/worker/workers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(service, times(1)).findById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("removeTool success test")
    void removeToolFromWorkerTest() throws Exception{
        Worker worker = new Worker();
        worker.setId(1L);
        AbstractTool tool = new CuttingTool();
        tool.setId(10L);
        tool.setQuantity(500);
        worker.getTools().put(tool, 100);

        when(service.removeToolFromWorker(1, 10, 5)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.patch("/rest/worker/removeTool/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .param("toolId", "10")
                .param("quantity", "5"))
                .andExpect(status().isOk());
        verify(service, times(1)).removeToolFromWorker(1L, 10L, 5);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("removeTool failed test")
    void removeToolFromWorkerFailedTest() throws Exception{
        Worker worker = new Worker();
        worker.setId(1L);
        AbstractTool tool = new CuttingTool();
        tool.setId(10L);
        tool.setQuantity(10);
        worker.getTools().put(tool, 5);

        when(service.removeToolFromWorker(1, 10, 6)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.patch("/rest/worker/removeTool/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .param("toolId", "10")
                .param("quantity", "6"))
                .andExpect(status().is3xxRedirection());
        verify(service, times(1)).removeToolFromWorker(1L, 10L, 6);
        verifyNoMoreInteractions(service);
    }
}