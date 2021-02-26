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
    ProductService service;
    @Autowired
    GeneralToolService toolService;

    @MockBean
    ProductRepository repository;
    @MockBean
    AbstractToolRepository toolRepository;

    //Product methods

    @Test
    @DisplayName("findAll test")
    void findAllTest() {
        Product product1 = new Product();
        Product product2 = new Product();

        doReturn(Arrays.asList(product1, product2)).when(repository).findAll();
        List<Product> products = service.findAll();

        Assertions.assertEquals(2, products.size(),
                "should find 2 products");
    }

    @Test
    @DisplayName("findAll test with empty List")
    void findAllWithEmptyTest() {
        doReturn(Collections.emptyList()).when(repository).findAll();
        List<Product> products = service.findAll();

        Assertions.assertEquals(0, products.size(),
                "should find nothing");
    }

    @Test
    @DisplayName("findById success")
    void findByIdSuccessTest() {
        Product product = new Product();
        product.setId(1L);

        doReturn(Optional.of(product)).when(repository).findById(1L);
        Optional<Product> returnedProduct = service.findById(1L);

        Assertions.assertTrue(returnedProduct.isPresent(),
                "Product was not found");
        Assertions.assertSame(returnedProduct.get(), product,
                "Returned product is not the same as mock");
    }

    @Test
    @DisplayName("findById not found")
    void findByIdNotFoundTest() {
        doReturn(Optional.empty()).when(repository).findById(1L);
        Optional<Product> returnedProduct = service.findById(1L);

        Assertions.assertFalse(returnedProduct.isPresent(),
                "Product shouldn't be found");
    }

    @Test
    @DisplayName("save product test")
    void saveTest() {
        Product product = new Product();

        doReturn(product).when(repository).save(any());
        Product returnedProduct = service.save(product);

        Assertions.assertNotNull(returnedProduct,
                "Saved product shouldn't be null");
        Assertions.assertEquals(returnedProduct, product,
                "Both products should be equals");
    }

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

        doReturn(Arrays.asList(tool1, tool2, tool3, tool4)).when(toolRepository).findAll();
        List<SupportTool> tools = toolService.findAllSupportTools();

        Assertions.assertEquals(2, tools.size(),
                "should find 2 tools");
    }

    @Test
    @DisplayName("getAllSupportToolsTest with empty List")
    void getAllSupportToolsEmptyTest() {
        AbstractTool tool1 = new CuttingTool();
        AbstractTool tool2 = new MeasuringTool();

        doReturn(Arrays.asList(tool1, tool2)).when(toolRepository).findAll();
        List<SupportTool> tools = toolService.findAllSupportTools();

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

        doReturn(Arrays.asList(tool1, tool2, tool3, tool4)).when(toolRepository).findAll();
        List<CuttingTool> tools = toolService.findAllCuttingTools();

        Assertions.assertEquals(2, tools.size(),
                "should find 2 tools");
    }

    @Test
    @DisplayName("getAllCuttingToolsTest with empty List")
    void getAllCuttingToolsEmptyTest() {
        AbstractTool tool1 = new SupportTool();
        AbstractTool tool2 = new MeasuringTool();

        doReturn(Arrays.asList(tool1, tool2)).when(toolRepository).findAll();
        List<CuttingTool> tools = toolService.findAllCuttingTools();

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

        doReturn(Arrays.asList(tool1, tool2, tool3, tool4)).when(toolRepository).findAll();
        List<MeasuringTool> tools = toolService.findAllMeasuringTools();

        Assertions.assertEquals(2, tools.size(),
                "should find 2 tools");
    }

    @Test
    @DisplayName("getAllSupportToolsTest with empty List")
    void getAllMeasuringToolsEmptyTest() {
        AbstractTool tool1 = new CuttingTool();
        AbstractTool tool2 = new SupportTool();

        doReturn(Arrays.asList(tool1, tool2)).when(toolRepository).findAll();
        List<MeasuringTool> tools = toolService.findAllMeasuringTools();

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

        service.save(product);
        Optional<Product> returnedProduct = service.findById(1L);
        Assertions.assertFalse(returnedProduct.get().getToolSet().contains(tool),
                "Shouldn't contain tool");

        service.addTool(1L, 10L);
        returnedProduct = service.findById(1L);
        Assertions.assertTrue(returnedProduct.get().getToolSet().contains(tool),
                "Should contain tool");

    }
}