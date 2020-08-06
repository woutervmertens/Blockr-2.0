package com.swop.command;

import com.swop.*;
import com.swop.blocks.ActionBlockModel;
import com.swop.blocks.StdBlockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class DeleteBlockCommandTest {

    GameController gameController;
    ActionBlockModel a;
    GameCommand c;
    ProgramAreaViewModel pavm;

    @BeforeEach
    void setUp() {
        gameController = new GameController(new MyGameWorldType());
        pavm = new ProgramAreaViewModel(new Point(0,0),10,10,new WindowGameControllerFacade(gameController));
        gameController.setProgramAreaVM(pavm);
        GameWorldViewModel gwvm = new GameWorldViewModel(new Point(0,0),0,0);
        gameController.setGameWorldVM(gwvm);
        PaletteViewModel pvm = new PaletteViewModel(new Point(0,0),0,0,new WindowGameControllerFacade(gameController));
        gameController.setPaletteVM(pvm);
        ScrollBarViewModel sbvm = new ScrollBarViewModel(new Point(0,0),0,0);
        pavm.addScrollBar(sbvm);
        gwvm.addScrollBar(sbvm);
        pvm.addScrollBar(sbvm);
    }

    @Test
    void execute() {
        a = new ActionBlockModel(new StdBlockData(new Point(1,1),0,0,""),null);
        new AddBlockCommand(new CommandGameControllerFacade(gameController),a).execute();
        assertEquals(SuccessState.SUCCESS,gameController.getLastSuccessState());
        c = new DeleteBlockCommand(new CommandGameControllerFacade(gameController),a);
        c.execute();
        assertFalse(pavm.getModel().getAllBlocks().contains(a));
    }

    @Test
    void undo() {
        execute();
        c.undo();
        assertTrue(pavm.getModel().getAllBlocks().size() == 1);
    }
}