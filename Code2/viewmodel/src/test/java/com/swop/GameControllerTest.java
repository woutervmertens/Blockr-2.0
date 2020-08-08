package com.swop;


import com.swop.blocks.*;
import com.swop.command.AddBlockCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

class GameControllerTest {
    GameController gc;
    StdBlockData s;
    ProgramAreaViewModel pavm;

    @BeforeEach
    void setUp() {
        gc = new GameController(new MyGameWorldType());
        pavm = new ProgramAreaViewModel(new Point(0,0),10,10,new WindowGameControllerFacade(gc));
        gc.setProgramAreaVM(pavm);
        GameWorldViewModel gwvm = new GameWorldViewModel(new Point(10,0),10,10);
        gc.setGameWorldVM(gwvm);
        PaletteViewModel pvm = new PaletteViewModel(new Point(20,0),10,10,new WindowGameControllerFacade(gc));
        gc.setPaletteVM(pvm);
        ScrollBarViewModel sbvm = new ScrollBarViewModel(new Point(0,0),0,0);
        pavm.addScrollBar(sbvm);
        gwvm.addScrollBar(sbvm);
        pvm.addScrollBar(sbvm);
        s = new StdBlockData(new Point(0,0),5,5,"test");
    }

    @Test
    void handleMousePress() {
        gc.HandleMousePress(1,1);
        assertTrue(RepaintEventController.getInstance().ShouldRepaint());
    }

    @Test
    void callReleaseInVms() {
        gc.setDraggedBlockVM(new ActionBlockModel(s,MyAction.MOVE_DOWN));
        gc.CallReleaseInVms(1,1);
        assertNull(gc.getDraggedBlockVM());
        gc.setDraggedBlockVM(new ActionBlockModel(s,MyAction.MOVE_DOWN));
        gc.CallReleaseInVms(11,1);
        assertNull(gc.getDraggedBlockVM());
        gc.setDraggedBlockVM(new ActionBlockModel(s,MyAction.MOVE_DOWN));
        gc.CallReleaseInVms(21,1);
        assertNull(gc.getDraggedBlockVM());
        gc.setDraggedBlockVM(new ActionBlockModel(s,MyAction.MOVE_DOWN));
        gc.CallReleaseInVms(-1,1);
        assertNull(gc.getDraggedBlockVM());
    }

    @Test
    void handleMouseDrag() {
        gc.HandleMouseDrag(50,50);
        assertTrue(RepaintEventController.getInstance().ShouldRepaint());
        gc.setDraggedBlockVM(new ActionBlockModel(s,MyAction.MOVE_DOWN));
        gc.HandleMouseDrag(50,50);
        assertEquals(50,gc.getDraggedBlockVM().getPosition().x);
        assertTrue(RepaintEventController.getInstance().ShouldRepaint());
    }

    @Test
    void getNrBlocksAvailable() {
        assertEquals(20,gc.getNrBlocksAvailable());
        pavm.DropBlock(new ActionBlockModel(s,MyAction.MOVE_DOWN));
        assertEquals(19,gc.getNrBlocksAvailable());
    }

    @Test
    void callResetCommand() {
        gc.executeCommand(new AddBlockCommand(new CommandGameControllerFacade(gc),new ActionBlockModel(s,MyAction.MOVE_DOWN)));
        assertEquals(19,gc.getNrBlocksAvailable());
        gc.undoCommand();
        assertEquals(20,gc.getNrBlocksAvailable());
        gc.redoCommand();
        assertEquals(19,gc.getNrBlocksAvailable());
        gc.callResetCommand();
        assertEquals(19,gc.getNrBlocksAvailable());
    }

    @Test
    void resetExecution() {
        gc.resetExecution();
        assertEquals(SuccessState.FAILURE,gc.getLastSuccessState());
        assertTrue(RepaintEventController.getInstance().ShouldRepaint());
    }

    @Test
    void restoreSnapshot() {
        GameSnapshot snap = gc.createSnapshot();
        gc.executeCommand(new AddBlockCommand(new CommandGameControllerFacade(gc),new ActionBlockModel(s,MyAction.MOVE_DOWN)));
        assertEquals(19,gc.getNrBlocksAvailable());
        gc.restoreSnapshot(snap);
        assertEquals(20,gc.getNrBlocksAvailable());
    }

    @Test
    void deleteAddBlock() {
        FunctionDefinitionBlockModel f = new FunctionDefinitionBlockModel(s);
        gc.addBlock(f);
        assertEquals(19,gc.getNrBlocksAvailable());
        gc.deleteBlock(f);
        assertEquals(20,gc.getNrBlocksAvailable());
    }

    @Test
    void dropDraggedBlock() {
        FunctionDefinitionBlockModel f = new FunctionDefinitionBlockModel(s);
        f.setPosition(new Point(1,1));
        gc.setDraggedBlockVM(f);
        gc.dropDraggedBlock();
        assertEquals(19,gc.getNrBlocksAvailable());
        f.setPosition(new Point(-1,-1));
        gc.setDraggedBlockVM(f);
        gc.dropDraggedBlock();
        assertEquals(20,gc.getNrBlocksAvailable());
    }
}