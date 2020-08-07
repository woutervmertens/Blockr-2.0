package com.swop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ProgramAreaViewModelTest {

    GameController gc;
    ProgramAreaViewModel pavm;

    @BeforeEach
    void setUp() {
        gc = new GameController(new MyGameWorldType());
        pavm = new ProgramAreaViewModel(new Point(0,0),300,600,new WindowGameControllerFacade(gc));
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
    void getModel() {
    }

    @Test
    void setModel() {
    }

    @Test
    void getAllBlocks() {
    }

    @Test
    void dropBlock() {
    }

    @Test
    void removeBlock() {
    }

    @Test
    void executeNext() {
    }

    @Test
    void getNextToExecute() {
    }

    @Test
    void setHighlight() {
    }

    @Test
    void generateProgram() {
    }

    @Test
    void getAllBlockVMs() {
    }

    @Test
    void getNumBlocksUsed() {
    }
}