package com.toolsapp.controller.extra.product;

import com.toolsapp.domain.extra.Product;
import com.toolsapp.repository.extra.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    ProductController controller;

//    @MockBean
//    ProductRepository repository;

    @MockBean
    BindingResult bindingResult;

//    @Before
//    void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//    }

    @After
    void reset() {
        mockMvc = null;
    }

    @Test
    @DisplayName("showListOfProduct test")
    void showListOfProductTest() throws Exception {
        this.mockMvc.perform(get("/product/products"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("/product/products"))
                .andReturn();
    }

    @Test
    @DisplayName("addProduct GET test")
    void addProductTest() throws Exception {
        this.mockMvc.perform(get("/product/addProduct"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("/product/addProduct"))
                .andReturn();
    }

    @Test
    @DisplayName("addProduct POST success test")
    void addNewProductTest() throws Exception {
        this.mockMvc
                .perform(post("/product/addProduct")
                        .param("name", "newProductName")
                        .param("toolId", "1")
                        .param("toolId", "1")
                        .param("toolId", "1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/products"))
                .andReturn();
    }

    @Test
    @DisplayName("addProduct POST empty name field test")
    void addNewProductWithEmptyFieldsTest() throws Exception {
        this.mockMvc
                .perform(post("/product/addProduct")
                .param("name", "")
                .param("toolId", "1")
                .param("toolId", "1")
                .param("toolId", "1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("/product/addProduct"))
                //TODO regexp
//                .andExpect(content().string("Введите наименование"))
                .andReturn();
    }

//    @Test
//    @DisplayName("addProduct POST, tool is already exists")
//    void addNewProductFailedTest() throws Exception {
//        this.mockMvc
//                .perform(post("/product/addProduct")
//                        .param("name", "newProduct")
//                        .param("toolId", "1")
//                        .param("toolId", "1")
//                        .param("toolId", "1"))
//                .andReturn();
//        this.mockMvc
//                .perform(post("/product/addProduct")
//                .param("name", "newProduct")
//                .param("toolId", "2")
//                .param("toolId", "2")
//                .param("toolId", "2"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().is5xxServerError())
//                .andExpect(view().name("/product/addProduct"))
////                .andExpect(content().string("такой объект уже существует"))
//                .andReturn();
//    }

//    @Test
//    @DisplayName("singeProductView test")
//    void singeProductViewTest() throws Exception {
////        this.mockMvc
////                .perform(post("/product/addProduct")
////                        .param("name", "newProductName")
////                        .param("toolId", "1")
////                        .param("toolId", "1")
////                        .param("toolId", "1"))
////                .andReturn();
//        Product product = new Product();
//        product.setId(1L);
//        product.setName("name");
//        this.mockMvc
//                .perform(get("/product/products/1"))
////                        .param())
//                .andExpect(status().isOk())
//                .andExpect(view().name("/product/products/1"))
//                .andReturn();
//    }

    @Test
    void addSupportTool() {
    }

    @Test
    void addCuttingTool() {
    }

    @Test
    void addTool() {
    }

    @Test
    void deleteProduct() {
    }
}