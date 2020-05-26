package com.swop.blocks;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FunctionDefinitionBlockTest {
    private final Point position = new Point(5, 1);
    private final int width = 3;
    private final int height = 4;
    private final FunctionDefinitionBlock definition = new FunctionDefinitionBlock(position,width,height);
    @Test
    void handleEndOfBody() {
        definition.setBusy(true);
        definition.handleEndOfBody();
        assertFalse(definition.isBusy());
    }
}