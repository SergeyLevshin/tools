package com.toolsapp.domain.tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MeasuringToolTest {

    private MeasuringTool tool;

    @BeforeEach
    void init() {
        tool = new MeasuringTool();
    }

    @Test
    @DisplayName("should check")
    void needToCheckSuccessTest() {
        LocalDate currentDate = LocalDate.now().minusDays(10);
        tool.setCheckDate(currentDate);
        assertTrue(tool.needToCheck());
    }

    @Test
    @DisplayName("shouldn't check")
    void needToCheckFailedTest() {
        assertFalse(tool.needToCheck());
    }
}