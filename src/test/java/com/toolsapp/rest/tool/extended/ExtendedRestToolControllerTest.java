package com.toolsapp.rest.tool.extended;

import com.toolsapp.service.tools.extended.ExtendedGeneralToolService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExtendedRestToolController.class)
public class ExtendedRestToolControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ExtendedGeneralToolService service;

    private final String uri = "/rest/tool/general/";

    @Test
    @DisplayName("giveTool success test")
    void giveToolTest() throws Exception {
        long toolId = 1L;
        int quantity = 10;
        long workerId = 15L;

        when(service.giveToolToWorker(toolId, quantity, workerId)).thenReturn(true);

        mockMvc.perform(patch(uri + "/giveToWorker/{workerId}", workerId)
                .contentType(MediaType.APPLICATION_JSON)
                .param("toolId", "1")
                .param("quantity", "10"))
                .andExpect(status().isOk());
        verify(service, times(1)).giveToolToWorker(toolId, quantity, workerId);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("giveTool failed test")
    void giveToolFailedTest() throws Exception {
        long toolId = 1L;
        int quantity = 10;
        long workerId = 15L;

        when(service.giveToolToWorker(toolId, quantity, workerId)).thenReturn(false);

        mockMvc.perform(patch(uri + "/giveToWorker/{workerId}", workerId)
                .contentType(MediaType.APPLICATION_JSON)
                .param("toolId", "1")
                .param("quantity", "10"))
                .andExpect(status().is3xxRedirection());
        verify(service, times(1)).giveToolToWorker(toolId, quantity, workerId);
        verifyNoMoreInteractions(service);
    }
}
