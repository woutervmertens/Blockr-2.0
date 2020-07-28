package com.swop.GameStates;

import com.swop.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class defaultStateTest {
    GameState state;
    GameController gameController;

    @BeforeEach
    void setUp() {
        state = new defaultState();
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
        assertEquals(state,state.execute(gameController));
    }

    @Test
    void release() {
        assertEquals(state,state.release(gameController,1,1));
    }

    @Test
    void getFeedback() {
        assertEquals("# blocks available: 7",state.getFeedback(7));
    }
}