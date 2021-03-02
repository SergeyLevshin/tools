package com.toolsapp.service.extra.product;

import com.toolsapp.domain.extra.Product;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.domain.tools.MeasuringTool;
import com.toolsapp.domain.tools.SupportTool;
import com.toolsapp.repository.extra.ProductRepository;
import com.toolsapp.repository.tools.AbstractToolRepository;
import com.toolsapp.service.tools.common.GeneralToolService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;
    @Autowired
    GeneralToolService toolService;

    @MockBean
    ProductRepository repository;
    @MockBean
    AbstractToolRepository toolRepository;

    //Tool methods

    @Test
    @DisplayName("getAllTools success test")
    void getAllToolsTest() {
        AbstractTool tool1 = new SupportTool();
        AbstractTool tool2 = new CuttingTool();
        AbstractTool tool3 = new MeasuringTool();

        doReturn(Arrays.asList(tool1, tool2, tool3)).when(toolRepository).findAll();
        List<AbstractTool> tools = toolService.findAll();

        Assertions.assertEquals(3, tools.size(),
                "should find 3 tools");
    }

    @Test
    @DisplayName("getAllTools test with empty List")
    void getAllToolsEmptyTest() {
        doReturn(Collections.emptyList()).when(toolRepository).findAll();
        List<AbstractTool> tools = toolService.findAll();

        Assertions.assertEquals(0, tools.size(),
                "should find nothing");
    }


    @Test
    @DisplayName("getAllSupportToolsTest success")
    void getAllSupportToolsTest() {
        AbstractTool tool1 = new SupportTool();
        AbstractTool tool2 = new CuttingTool();
        AbstractTool tool3 = new MeasuringTool();
        AbstractTool tool4 = new SupportTool();

        doReturn(Arrays.asList(tool1, tool2, tool3, tool4)).when(toolRepository).findAllByOrderByNameAsc();
        List<SupportTool> tools = productService.getAllSupportTools();

        Assertions.assertEquals(2, tools.size(),
                "should find 2 tools");
    }

    @Test
    @DisplayName("getAllSupportToolsTest with empty List")
    void getAllSupportToolsEmptyTest() {
        AbstractTool tool1 = new CuttingTool();
        AbstractTool tool2 = new MeasuringTool();

        doReturn(Arrays.asList(tool1, tool2)).when(toolRepository).findAllByOrderByNameAsc();
        List<SupportTool> tools = productService.getAllSupportTools();

        Assertions.assertEquals(0, tools.size(),
                "should find 2 tools");
    }

    @Test
    @DisplayName("getAllCuttingToolsTest success")
    void getAllCuttingToolsTest() {
        AbstractTool tool1 = new SupportTool();
        AbstractTool tool2 = new CuttingTool();
        AbstractTool tool3 = new MeasuringTool();
        AbstractTool tool4 = new CuttingTool();

        doReturn(Arrays.asList(tool1, tool2, tool3, tool4)).when(toolRepository).findAllByOrderByNameAsc();
        List<CuttingTool> tools = productService.getAllCuttingTools();

        Assertions.assertEquals(2, tools.size(),
                "should find 2 tools");
    }

    @Test
    @DisplayName("getAllCuttingToolsTest with empty List")
    void getAllCuttingToolsEmptyTest() {
        AbstractTool tool1 = new SupportTool();
        AbstractTool tool2 = new MeasuringTool();

        doReturn(Arrays.asList(tool1, tool2)).when(toolRepository).findAllByOrderByNameAsc();
        List<CuttingTool> tools = productService.getAllCuttingTools();

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

        doReturn(Arrays.asList(tool1, tool2, tool3, tool4)).when(toolRepository).findAllByOrderByNameAsc();
        List<MeasuringTool> tools = productService.getAllMeasuringTools();

        Assertions.assertEquals(2, tools.size(),
                "should find 2 tools");
    }

    @Test
    @DisplayName("getAllSupportToolsTest with empty List")
    void getAllMeasuringToolsEmptyTest() {
        AbstractTool tool1 = new CuttingTool();
        AbstractTool tool2 = new SupportTool();

        doReturn(Arrays.asList(tool1, tool2)).when(toolRepository).findAllByOrderByNameAsc();
        List<MeasuringTool> tools = productService.getAllMeasuringTools();

        Assertions.assertEquals(0, tools.size(),
                "should find 2 tools");
    }

    @Test
    @DisplayName("addTool success test")
    void addTool() {
        Product product = new Product();
        product.setId(1L);
        AbstractTool tool = new CuttingTool();
        tool.setId(10L);

        doReturn(Optional.of(product)).when(repository).findById(1L);
        doReturn(Optional.of(tool)).when(toolRepository).findById(10L);
        doReturn(product).when(repository).save(any());

        productService.save(product);
        Optional<Product> returnedProduct = productService.findById(1L);
        Assertions.assertFalse(returnedProduct.get().getToolSet().contains(tool),
                "Shouldn't contain tool");

        productService.addTool(1L, 10L);
        returnedProduct = productService.findById(1L);
        Assertions.assertTrue(returnedProduct.get().getToolSet().contains(tool),
                "Should contain tool");

    }
}