package com.toolsapp.service.tools.common;

import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.repository.extra.WorkerRepository;
import com.toolsapp.repository.tools.CuttingToolsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class AbstractToolServiceTest {

    @Mock
    WorkerRepository workerRepository;

    @Mock
    CuttingToolsRepository repository;
    
    @InjectMocks
    CuttingToolService service;

    @Test
    @DisplayName("save property test")
    void saveTest() {
        CuttingTool tool = new CuttingTool();

        doReturn(tool).when(repository).save(any());
        CuttingTool returnedTool = service.save(tool);

        Assertions.assertNotNull(returnedTool,
                "Saved tool shouldn't be null");
        Assertions.assertEquals(returnedTool, tool,
                "Both tools should be equals");
    }

    @Test
    @DisplayName("findAll success test")
    void findAllTest() {
        CuttingTool tool1 = new CuttingTool();
        CuttingTool tool2 = new CuttingTool();

        doReturn(Arrays.asList(tool1, tool2)).when(repository).findAll();
        List<CuttingTool> tools = service.findAll();

        Assertions.assertEquals(2, tools.size(),
                "should find 2 tools");
    }

    @Test
    @DisplayName("findAll with empty list test")
    void findAllEmptyTest() {
        doReturn(Collections.emptyList()).when(repository).findAll();
        List<CuttingTool> tools = service.findAll();

        Assertions.assertEquals(0, tools.size(),
                "should find nothing");
    }

    @Test
    @DisplayName("findById success")
    void findByIdSuccessTest() {
        CuttingTool tool = new CuttingTool();
        tool.setId(1L);

        doReturn(Optional.of(tool)).when(repository).findById(1L);
        Optional<CuttingTool> returnedTool = service.findById(1L);

        Assertions.assertTrue(returnedTool.isPresent(),
                "tool was not found");
        Assertions.assertSame(returnedTool.get(), tool,
                "Returned tool is not the same as mock");
    }

    @Test
    @DisplayName("findById not found")
    void findByIdNotFoundTest() {
        doReturn(Optional.empty()).when(repository).findById(1L);
        Optional<CuttingTool> returnedTool = service.findById(1L);

        Assertions.assertFalse(returnedTool.isPresent(),
                "Tool shouldn't be found");
    }

    @Test
    @DisplayName("deleteById success test")
    void deleteByIdTest() {
        CuttingTool tool = new CuttingTool();
        tool.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(tool));
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}