package com.swop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PaletteViewModelTest {

    GameController gc;
    PaletteViewModel pvm;
    @BeforeEach
    void setUp() {
        gc = new GameController(new MyGameWorldType());
        pvm = new PaletteViewModel(new Point(0,0),300,600,new WindowGameControllerFacade(gc));
    }

    @Test
    void getAllButtons() {
    }

    @Test
    void addFuncCallButton() {
    }

    @Test
    void removeFuncCallButtons() {
    }

    @Test
    void adjustDefinitionButton() {
    }

    @Test
    void reactToBlockCreate() {
    }

    @Test
    void reactToBlockRemove() {
    }

    @Test
    void handleMousePress() {
    }

    @Test
    void handleMouseRelease() {
    }

    @Test
    void handleMouseDrag() {
    }

    @Test
    void handleReset() {
    }

    @Test
    void setWidth() {
    }
}