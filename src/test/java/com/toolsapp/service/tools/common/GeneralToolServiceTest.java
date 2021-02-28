package com.toolsapp.service.tools.common;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.domain.tools.MeasuringTool;
import com.toolsapp.domain.tools.SupportTool;
import com.toolsapp.repository.CommonRepository;
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
class GeneralToolServiceTest {

    @Mock
    CommonRepository<AbstractTool> repository;

    @InjectMocks
    GeneralToolService service;

    @Test
    @DisplayName("findAll test")
    void findAllToolsTest() {
        AbstractTool tool1 = new SupportTool();
        AbstractTool tool2 = new CuttingTool();
        AbstractTool tool3 = new MeasuringTool();

        doReturn(Arrays.asList(tool1, tool2, tool3)).when(repository).findAllByOrderByNameAsc();
        List<AbstractTool> tools = service.findAllSortByName();

        Assertions.assertEquals(3, tools.size(),
                "should find 3 tools");
    }

    @Test
    @DisplayName("getAllTools test with empty List")
    void findAllEmptyTest() {
        doReturn(Collections.emptyList()).when(repository).findAllByOrderByNameAsc();
        List<AbstractTool> tools =  service.findAllSortByName();

        Assertions.assertEquals(0, tools.size(),
                "should find nothing");
    }

    @Test
    @DisplayName("deleteById success test")
    void deleteToolTest() {
        AbstractTool tool = new CuttingTool();
        tool.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(tool));
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName(("findAllCuttingTools success test"))
    void findAllSupportToolsTest() {
        AbstractTool tool1 = new SupportTool();
        AbstractTool tool2 = new CuttingTool();
        AbstractTool tool3 = new MeasuringTool();
        AbstractTool tool4 = new SupportTool();

        doReturn(Arrays.asList(tool1, tool2, tool3, tool4)).when(repository).findAllByOrderByNameAsc();
        List<SupportTool> tools = service.findAllSupportTools();

        Assertions.assertEquals(2, tools.size(),
                "should find 2 tools");
    }

    @Test
    @DisplayName("findAllSupportToolsTest with empty List")
    void getAllSupportToolsEmptyTest() {
        AbstractTool tool1 = new CuttingTool();
        AbstractTool tool2 = new MeasuringTool();

        doReturn(Arrays.asList(tool1, tool2)).when(repository).findAllByOrderByNameAsc();
        List<SupportTool> tools = service.findAllSupportTools();

        Assertions.assertEquals(0, tools.size(),
                "should find 2 tools");
    }

    @Test
    @DisplayName("findAllCuttingToolsTest success")
    void getAllCuttingToolsTest() {
        AbstractTool tool1 = new SupportTool();
        AbstractTool tool2 = new CuttingTool();
        AbstractTool tool3 = new MeasuringTool();
        AbstractTool tool4 = new CuttingTool();

        doReturn(Arrays.asList(tool1, tool2, tool3, tool4)).when(repository).findAllByOrderByNameAsc();
        List<CuttingTool> tools = service.findAllCuttingTools();

        Assertions.assertEquals(2, tools.size(),
                "should find 2 tools");
    }

    @Test
    @DisplayName("getAllCuttingToolsTest with empty List")
    void getAllCuttingToolsEmptyTest() {
        AbstractTool tool1 = new SupportTool();
        AbstractTool tool2 = new MeasuringTool();

        doReturn(Arrays.asList(tool1, tool2)).when(repository).findAllByOrderByNameAsc();
        List<CuttingTool> tools = service.findAllCuttingTools();

        Assertions.assertEquals(0, tools.size(),
                "should find 2 tools");
    }

    @Test
    @DisplayName("getAllMeasuringToolsTest success")
    void getAllMeasuringToolsTest() {
        AbstractTool tool1 = new SupportTool();
        AbstractTool tool2 = new CuttingTool();
        AbstractTool tool3 = new MeasuringTool();
        AbstractTool tool4 = new MeasuringTool();

        doReturn(Arrays.asList(tool1, tool2, tool3, tool4)).when(repository).findAllByOrderByNameAsc();
        List<MeasuringTool> tools = service.findAllMeasuringTools();

        Assertions.assertEquals(2, tools.size(),
                "should find 2 tools");
    }

    @Test
    @DisplayName("getAllSupportToolsTest with empty List")
    void getAllMeasuringToolsEmptyTest() {
        AbstractTool tool1 = new CuttingTool();
        AbstractTool tool2 = new SupportTool();

        doReturn(Arrays.asList(tool1, tool2)).when(repository).findAllByOrderByNameAsc();
        List<MeasuringTool> tools = service.findAllMeasuringTools();

        Assertions.assertEquals(0, tools.size(),
                "should find 2 tools");
    }
}