package com.toolsapp.service.tools.extended;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.repository.extra.WorkerRepository;
import com.toolsapp.repository.tools.CuttingToolsRepository;
import com.toolsapp.service.extra.worker.WorkerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
class ExtendedToolServiceTest {

    @MockBean
    CuttingToolsRepository toolRepository;
    @MockBean
    WorkerRepository workerRepository;

    @Autowired
    WorkerService workerService;

    @Autowired
    ExtendedCuttingToolService service;

    @Test
    @DisplayName("giveToolToWorker success test")
    void giveToolToWorkerTest() {
        CuttingTool tool = new CuttingTool();
        tool.setId(1L);
        tool.setQuantity(10);
        Worker worker = new Worker();
        worker.setId(2L);

        service.save(tool);
        workerService.save(worker);

        doReturn(Optional.of(worker)).when(workerRepository).findById(2L);
        doReturn(Optional.of(tool)).when(toolRepository).findById(1L);

        //method didn't run
        Assertions.assertEquals(10, tool.getQuantity(),
                "should be 10 tool, no tools given");
        Assertions.assertNull(worker.getTools().get(tool),
                "should be no tools taken");

        //quantity = 0, no tool should be given
        service.giveToolToWorker(1l, 0, 2L);
        tool = service.findById(1L).get();
        worker = workerService.findById(2L).get();
        Assertions.assertEquals(10, tool.getQuantity(),
                "should be 10 tool, 0 tools given");
        Assertions.assertNull(worker.getTools().get(tool),
                "should be 0 tools taken");

        //normal quantity
        service.giveToolToWorker(1l, 3, 2L);
        tool = service.findById(1L).get();
        worker = workerService.findById(2L).get();
        System.out.println(tool + " / " + worker);
        Assertions.assertEquals(7, tool.getQuantity(),
                "should be 7 tools left");
        Assertions.assertEquals(3, worker.getTools().get(tool),
                "should be 3 tools taken");

        //quantity is larger than toolQuantity
        service.giveToolToWorker(1l, 100, 2L);
        tool = service.findById(1L).get();
        worker = workerService.findById(2L).get();
        System.out.println(tool + " / " + worker);
        Assertions.assertEquals(7, tool.getQuantity(),
                "should be 7 tools left, the tool shouldn't be given");
        Assertions.assertEquals(3, worker.getTools().get(tool),
                "should be 3 tools taken, 0 tools taken");

        //quantity equals toolQuantity, all tools shoild be given
        service.giveToolToWorker(1l, 7, 2L);
        tool = service.findById(1L).get();
        worker = workerService.findById(2L).get();
        System.out.println(tool + " / " + worker);
        Assertions.assertEquals(0, tool.getQuantity(),
                "should be 7 tools left, the tool shouldn't be given");
        Assertions.assertEquals(10, worker.getTools().get(tool),
                "should be 3 tools taken, 0 tools taken");
    }
}