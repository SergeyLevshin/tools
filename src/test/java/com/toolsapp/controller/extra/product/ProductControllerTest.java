package com.toolsapp.controller.extra.product;

import com.toolsapp.controller.extra.ProductController;
import com.toolsapp.service.extra.product.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService service;

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