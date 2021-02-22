package com.toolsapp.domain.extra;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.domain.tools.SupportTool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductTest {

    private final Product product = new Product();
    private AbstractTool tool;

    @BeforeEach
    void init() {
        tool = new SupportTool();
    }

    @Test
    @DisplayName("addTool test simple scenario")
    void tollAdded() {
        product.addTool(tool);
        assertTrue(product.getToolSet().contains(tool));
    }

    @Test
    @DisplayName("addTool test with no tool added")
    void toolNotAdded() {
        assertFalse(product.getToolSet().contains(tool));
    }
}