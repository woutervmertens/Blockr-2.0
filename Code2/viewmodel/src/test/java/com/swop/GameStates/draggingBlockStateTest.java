package com.swop.GameStates;

import com.swop.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class draggingBlockStateTest {

    GameState state;
    GameController gameController;

    @BeforeEach
    void setUp() {
        state = new draggingBlockState();
        gameController = new GameController(new MyGameWorldType());
        ProgramAreaViewModel pavm = new ProgramAreaViewModel(new Point(0,0),0,0,gameController);
        gameController.setProgramAreaVM(pavm);
        GameWorldViewModel gwvm = new GameWorldViewModel(new Point(0,0),0,0);
        gameController.setGameWorldVM(gwvm);
        PaletteViewModel pvm = new PaletteViewModel(new Point(0,0),0,0,gameController);
        gameController.setPaletteVM(pvm);
        ScrollBarViewModel sbvm = new ScrollBarViewModel(new Point(0,0),0,0);
        pavm.addScrollBar(sbvm);
        gwvm.addScrollBar(sbvm);
        pvm.addScrollBar(sbvm);
    }

    @Test
    void execute() {
        GameState gs = state.execute(gameController);
        assertTrue(gs instanceof defaultState);
    }

    @Test
    void release() {
        GameState gs = state.release(gameController,1,1);
        assertTrue(gs instanceof defaultState);
    }

    @Test
    void getFeedback() {
        assertEquals("",state.getFeedback());
    }
}