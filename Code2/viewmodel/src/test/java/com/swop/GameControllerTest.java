package com.swop;


import com.swop.blocks.ActionBlockModel;
import com.swop.blocks.ActionBlockVM;
import com.swop.blocks.BlockFactory;
import com.swop.blocks.StdBlockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

class GameControllerTest {
    GameController gc;
    StdBlockData s;

    @BeforeEach
    void setUp() {
        gc = new GameController(new MyGameWorldType());
        ProgramAreaViewModel pavm = new ProgramAreaViewModel(new Point(0,0),10,10,new WindowGameControllerFacade(gc));
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
    }

    @Test
    void getNrBlocksAvailable() {
    }

    @Test
    void undoCommand() {
    }

    @Test
    void redoCommand() {
    }

    @Test
    void executeCommand() {
    }

    @Test
    void executeNext() {
    }

    @Test
    void callResetCommand() {
    }

    @Test
    void resetExecution() {
    }

    @Test
    void getSupportedActions() {
    }

    @Test
    void getSupportedPredicates() {
    }

    @Test
    void getDraggedBlockVM() {
    }

    @Test
    void createSnapshot() {
    }

    @Test
    void restoreSnapshot() {
    }

    @Test
    void deleteBlock() {
    }

    @Test
    void addBlock() {
    }

    @Test
    void setPaletteVM() {
    }

    @Test
    void setProgramAreaVM() {
    }

    @Test
    void setGameWorldVM() {
    }

    @Test
    void dropDraggedBlock() {
    }

    @Test
    void setDraggedBlockVM() {
    }

    @Test
    void getLastSuccessState() {
    }

    @Test
    void setLastSuccessState() {
    }
}