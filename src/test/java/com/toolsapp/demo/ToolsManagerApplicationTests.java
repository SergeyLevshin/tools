package com.toolsapp.demo;

import com.toolsapp.repository.tools.CuttingToolsRepository;
import com.toolsapp.repository.extra.WorkerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ToolsManagerApplicationTests {

    @Autowired
    private CuttingToolsRepository toolsRepo;

    @Autowired
    private WorkerRepository workerRepo;

    @Test
    void contextLoads() {
        assertThat(toolsRepo).isNotNull();
        assertThat(workerRepo).isNotNull();
    }
}
