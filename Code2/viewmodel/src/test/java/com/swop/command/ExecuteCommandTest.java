package com.swop.command;

import com.swop.*;
import com.swop.blocks.ActionBlockModel;
import com.swop.blocks.StdBlockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ExecuteCommandTest {
    GameController gameController;
    ActionBlockModel a;
    GameCommand c;
    ProgramAreaViewModel pavm;
    GameWorldViewModel gwvm;

    @BeforeEach
    void setUp() {
        gameController = new GameController(new MyGameWorldType());
        pavm = new ProgramAreaViewModel(new Point(0,0),10,10,new WindowGameControllerFacade(gameController));
        gameController.setProgramAreaVM(pavm);
        gwvm = new GameWorldViewModel(new Point(0,0),0,0);
        gameController.setGameWorldVM(gwvm);
        PaletteViewModel pvm = new PaletteViewModel(new Point(0,0),0,0,new WindowGameControllerFacade(gameController));
        gameController.setPaletteVM(pvm);
        ScrollBarViewModel sbvm = new ScrollBarViewModel(new Point(0,0),0,0);
        pavm.addScrollBar(sbvm);
        gwvm.addScrollBar(sbvm);
        pvm.addScrollBar(sbvm);
        a = new ActionBlockModel(new StdBlockData(new Point(1,1),0,0,""),MyAction.MOVE_DOWN);
        c = new ExecuteCommand(new CommandGameControllerFacade(gameController),pavm, gwvm.getGameWorld());
    }

    @Test
    void execute() {
        pavm.DropBlock(a);
        pavm.GenerateProgram();
        MySnapshot ms = (MySnapshot) gwvm.getGameWorld().createSnapshot();
        assertEquals(0,ms.getEmptySquare().x);
        c.execute();
        ms = (MySnapshot) gwvm.getGameWorld().createSnapshot();
        assertEquals(1,ms.getEmptySquare().x);
    }
}