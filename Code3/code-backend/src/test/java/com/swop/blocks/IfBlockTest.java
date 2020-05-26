package com.swop.blocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class IfBlockTest {
    IfBlock block;
    Point position;
    int width, height;
    @BeforeEach
    void setUp(){
        position = new Point(1,1);
        width = 1;
        height = 1;
        block = new IfBlock(position,width,height);
    }

    @Test
    void handleEndOfBody() {
        block.handleEndOfBody();
        assertFalse(block.isBusy());
    }
}