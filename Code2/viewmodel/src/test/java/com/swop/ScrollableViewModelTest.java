package com.swop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ScrollableViewModelTest {
    ScrollableViewModel gwvm;

    @BeforeEach
    void setUp() {
        gwvm = new GameWorldViewModel(new Point(0,0),50,50);
        gwvm.addScrollBar(new ScrollBarViewModel(new Point(40,0),50,10));
    }

    @Test
    void getClipStart() {
        assertEquals(0,gwvm.getClipStart());
    }

    @Test
    void getFullHeight() {
        assertEquals(50,gwvm.getFullHeight());
        gwvm.increaseSize();
        assertEquals(100,gwvm.getFullHeight());
    }

    @Test
    void offsetScrollPosition() {
        assertEquals(50,gwvm.offsetScrollPosition(50));
    }

    @Test
    void isInScrollBuffer() {
        assertTrue(gwvm.isInScrollBuffer(new Point(1,1)));
        assertFalse(gwvm.isInScrollBuffer(new Point(-100,-110)));
        gwvm.increaseSize();
        gwvm.increaseSize();
        assertFalse(gwvm.isInScrollBuffer(new Point(1,1)));
        assertTrue(gwvm.isInScrollBuffer(new Point(1,51)));
    }
}