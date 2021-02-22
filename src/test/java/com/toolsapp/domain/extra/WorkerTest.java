package com.toolsapp.domain.extra;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.domain.tools.CuttingTool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WorkerTest {

    private final Worker worker = new Worker();
    private AbstractTool tool;

    @BeforeEach
    void init() {
        tool = new CuttingTool();
    }

    @Test
    @DisplayName("decreaseToolQuantity test simple scenario")
    void decreaseToolQuantityTest() {
        worker.getTools().put(tool, 10);
        worker.decreaseToolQuantity(tool, 5);
        assertEquals(worker.getTools().get(tool), 5);
    }

    @Test
    @DisplayName("decreaseToolQuantity test, tool should be removed")
    void decreaseToolQuantityWithNoToolAfterDecreaseTest() {
        worker.getTools().put(tool, 10);
        worker.decreaseToolQuantity(tool, 10);
        assertNull(worker.getTools().get(tool), "Should be no Tool exist");
    }

    @Test
    @DisplayName("decreaseToolQuantity test, try to remove more then exist")
    void decreaseToolQuantityWithQuantityGreaterThenCurrentTest() {
        worker.getTools().put(tool, 10);
        worker.decreaseToolQuantity(tool, 20);
        assertEquals(worker.getTools().get(tool), 10,
                "Quantity shouldn't decrease");
    }

    @Test
    @DisplayName("increaseToolQuantity test simple scenario")
    void increaseToolQuantityTest() {
        worker.getTools().put(tool, 10);
        worker.increaseToolQuantity(tool,10);
        assertEquals(worker.getTools().get(tool), 20);
    }

    @Test
    @DisplayName("increaseToolQuantity test, tool doesn't exist")
    void increaseToolQuantityWithNoToolPresentTest() {
        worker.increaseToolQuantity(tool,10);
        assertEquals(worker.getTools().get(tool), 10,
                "Tool should be added including quantity");
    }
}