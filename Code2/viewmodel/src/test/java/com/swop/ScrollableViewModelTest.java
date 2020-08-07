package com.swop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ScrollableViewModelTest {
    ScrollableViewModel gwvm;
    GameController gc;
    ProgramAreaViewModel pavm;

    @BeforeEach
    void setUp() {
        gwvm = new GameWorldViewModel(new Point(0,0),50,50);
        gc = new GameController(new MyGameWorldType());
        pavm = new ProgramAreaViewModel(new Point(0,0),300,600,new WindowGameControllerFacade(gc));
        gwvm.addScrollBar(new ScrollBarViewModel(new Point(40,0),50,10));
        pavm.addScrollBar(new ScrollBarViewModel(new Point(40,0),50,10));
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
        pavm.increaseSize();
        pavm.scrollBarViewModel.setActive(true);
        pavm.scrollBarViewModel.model.setHandleYPosition(1.0f);
        assertEquals(1200,pavm.offsetScrollPosition(600));
        assertEquals(600,pavm.offsetScrollPosition(0));
        pavm.scrollBarViewModel.model.setHandleYPosition(0.5f);
        assertEquals(900,pavm.offsetScrollPosition(600));
        assertEquals(300,pavm.offsetScrollPosition(0));
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