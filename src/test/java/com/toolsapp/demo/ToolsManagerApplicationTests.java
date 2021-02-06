package com.toolsapp.demo;

import com.toolsapp.controller.ToolsController;
import com.toolsapp.repository.tools.CuttingToolsRepository;
import com.toolsapp.repository.tools.WorkerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ToolsManagerApplicationTests {

    @Autowired
    private ToolsController controller;

    @Autowired
    private CuttingToolsRepository toolsRepo;

    @Autowired
    private WorkerRepository workerRepo;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
        assertThat(toolsRepo).isNotNull();
        assertThat(workerRepo).isNotNull();
    }
}
