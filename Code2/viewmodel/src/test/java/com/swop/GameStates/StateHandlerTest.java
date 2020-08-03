package com.swop.GameStates;

import com.swop.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.nimbus.State;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class StateHandlerTest {

    StateHandler sh;
    GameController gameController;

    @BeforeEach
    void setUp() {
        gameController = new GameController(new MyGameWorldType());
        ProgramAreaViewModel pavm = new ProgramAreaViewModel(new Point(0,0),0,0,new WindowGameControllerFacade(gameController));
        gameController.setProgramAreaVM(pavm);
        GameWorldViewModel gwvm = new GameWorldViewModel(new Point(0,0),0,0);
        gameController.setGameWorldVM(gwvm);
        PaletteViewModel pvm = new PaletteViewModel(new Point(0,0),0,0,new WindowGameControllerFacade(gameController));
        gameController.setPaletteVM(pvm);
        ScrollBarViewModel sbvm = new ScrollBarViewModel(new Point(0,0),0,0);
        pavm.addScrollBar(sbvm);
        gwvm.addScrollBar(sbvm);
        pvm.addScrollBar(sbvm);
        sh = new StateHandler(new InputGameControllerFacade(gameController));
    }

    @Test
    void handleMouseRelease() {
        sh.HandleMouseRelease(1,1);
    }

    @Test
    void getFeedback() {
        assertEquals("",sh.getFeedback());
    }

    @Test
    void handleExecute() {
        sh.HandleExecute();
    }

    @Test
    void setStateDrag() {
        sh.setStateDrag();
    }

    @Test
    void setStateGoal() {
        sh.setStateGoal();
        assertEquals("GOAL REACHED!",sh.getFeedback());
    }
}