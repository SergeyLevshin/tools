package com.toolsapp.controller;

import com.toolsapp.service.WorkerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
class WorkerControllerTest {

    @Mock
    private WorkerService workerService;

    private MockMvc mockMvc;

    @Test
    void showWorkers() {
    }

    @Test
    void singleWorkerToolsList() {
    }

    @Test
    void removeTool() {
    }
}