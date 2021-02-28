package com.toolsapp.controller.extra.product;

import com.toolsapp.controller.extra.ProductController;
import com.toolsapp.domain.extra.Product;
import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.repository.extra.ProductRepository;
import com.toolsapp.service.extra.product.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
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

    @MockBean
    ProductService service;

    @MockBean
    BindingResult bindingResult;

    @Before
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

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
                .andReturn();
    }
}