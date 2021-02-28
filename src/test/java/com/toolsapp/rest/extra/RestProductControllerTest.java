package com.toolsapp.rest.extra;

import com.toolsapp.domain.extra.Product;
import com.toolsapp.service.extra.product.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestProductControllerTest  {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService service;

    @Test
    @DisplayName("getAllProducts not empty list  test")
    void getAllProductsTest() throws Exception{
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("name1");
        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("name2");
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        when(service.findAllSortByName()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/product/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("name1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("name2"));
        verify(service, times(1)).findAllSortByName();
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("getAllProducts empty list test")
    void getAllProductsEmptyTest() throws Exception{
        when(service.findAllSortByName()).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/product/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(service, times(1)).findAllSortByName();
        verifyNoMoreInteractions(service);
    }


    @Test
    @DisplayName("getSingleProduct success test")
    void getSingleProductTest() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("name");

        when(service.findById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/product/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("name"))
                .andExpect(jsonPath("toolSet").value(new ArrayList<>()));
        verify(service, times(1)).findById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("getSingleProduct not found test")
    void getSingleProductNotFoundTest() throws Exception {

        when(service.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/product/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(service, times(1)).findById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    void addTool() {

    }

    @Test
    void addProduct() {
    }

    @Test
    void createProduct() {
    }

    @Test
    @DisplayName("deleteProduct success test")
    void deleteProductTest() throws Exception {
        when(service.deleteById(1L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/product/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("deleteProduct not found test")
    void deleteProductNotFoundTest() throws Exception {
        when(service.deleteById(1L)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/product/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
        verify(service, times(1)).deleteById(1L);
        verifyNoMoreInteractions(service);
    }
}