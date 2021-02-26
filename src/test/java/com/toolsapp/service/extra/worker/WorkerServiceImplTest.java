package com.toolsapp.service.extra.worker;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.domain.tools.MeasuringTool;
import com.toolsapp.repository.CommonRepository;
import com.toolsapp.repository.extra.WorkerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class WorkerServiceImplTest {

    @Autowired
    WorkerService service;

    @MockBean
    WorkerRepository repository;

    @Test
    @DisplayName("findAll test")
    void findAllTest() {
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();

        doReturn(Arrays.asList(worker1, worker2)).when(repository).findAll();
        List<Worker> workers = service.findAll();

        Assertions.assertEquals(2, workers.size(),
                "should find 2 workers");
    }

    @Test
    @DisplayName("findAll test with empty List")
    void findAllEmptyTest() {
        doReturn(Collections.emptyList()).when(repository).findAll();
        List<Worker> workers = service.findAll();

        Assertions.assertEquals(0, workers.size(),
                "should find nothing");
    }

    @Test
    @DisplayName("findById success")
    void findByIdSuccessTest() {
        Worker worker = new Worker();
        worker.setId(1L);

        doReturn(Optional.of(worker)).when(repository).findById(1L);
        Optional<Worker> returnedWorker = service.findById(1L);

        Assertions.assertTrue(returnedWorker.isPresent(),
                "Worker was not found");
        Assertions.assertSame(returnedWorker.get(), worker,
                "Returned worker is not the same as mock");
    }

    @Test
    @DisplayName("findById not found")
    void findByIdNotFoundTest() {
        doReturn(Optional.empty()).when(repository).findById(1L);
        Optional<Worker> returnedWorker = service.findById(1L);

        Assertions.assertFalse(returnedWorker.isPresent(),
                "Worker shouldn't be found");
    }

    @Test
    @DisplayName("save worker test")
    void saveTest() {
        Worker worker = new Worker();

        doReturn(worker).when(repository).save(any());
        Worker returnedWorker = service.save(worker);

        Assertions.assertNotNull(returnedWorker,
                "Saved worker shouldn't be null");
        Assertions.assertEquals(returnedWorker, worker,
                "Both workers should be equals");
    }

    @Test
    @DisplayName("getWorkerTool test")
    void getWorkerToolsTest() {
        Worker worker = new Worker();
        worker.setId(1L);
        Map<AbstractTool, Integer> toolMap = new HashMap<>();
        toolMap.put(new CuttingTool(), 10);
        toolMap.put(new MeasuringTool(), 100);
        worker.setTools(toolMap);

        doReturn(worker).when(repository).save(any());
        doReturn(Optional.of(worker)).when(repository).findById(1L);

        Map<AbstractTool, Integer> returnedMap = service.getWorkerTools(1L);
        Assertions.assertEquals(toolMap, returnedMap, "Both maps should be equals");
    }

    @Test
    @DisplayName("getWorkerTool test with empty")
    void getWorkerToolsEmptyTest() {
        Worker worker = new Worker();
        worker.setId(1L);
        Map<AbstractTool, Integer> toolMap = new HashMap<>();

        doReturn(worker).when(repository).save(any());
        doReturn(Optional.of(worker)).when(repository).findById(1L);

        Map<AbstractTool, Integer> returnedMap = service.getWorkerTools(1L);
        Assertions.assertEquals(toolMap, returnedMap, "Both maps should be equals");
    }

    @Test
    @DisplayName("removeToolFromWorker test")
    void removeToolFromWorkerTest() {
        Worker worker = new Worker();
        worker.setId(1L);
        Map<AbstractTool, Integer> toolMap = new HashMap<>();
        AbstractTool tool = new CuttingTool();
        tool.setId(10L);
        toolMap.put(tool, 100);
        worker.setTools(toolMap);

        doReturn(worker).when(repository).save(any());
        doReturn(Optional.of(worker)).when(repository).findById(1L);

        service.removeToolFromWorker(1L, 10L, 0);
        Map<AbstractTool, Integer> returnedMap = service.getWorkerTools(1L);
        Assertions.assertEquals(100, returnedMap.get(tool),"Shouldn't be subtracted");

        service.removeToolFromWorker(1L, 10L, 10);
        returnedMap = service.getWorkerTools(1L);
        Assertions.assertEquals(90, returnedMap.get(tool),"Should be subtracted");

        service.removeToolFromWorker(1L, 10L, 90);
        returnedMap = service.getWorkerTools(1L);
        Assertions.assertTrue(returnedMap.isEmpty(),"Tool with quantity = 0, should be removed");
    }
}