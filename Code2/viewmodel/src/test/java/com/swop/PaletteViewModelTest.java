package com.swop;

import com.swop.blocks.BlockModelType;
import com.swop.blocks.FunctionDefinitionBlockModel;
import com.swop.blocks.StdBlockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PaletteViewModelTest {

    GameController gc;
    PaletteViewModel pvm;
    StdBlockData s;
    @BeforeEach
    void setUp() {
        gc = new GameController(new MyGameWorldType());
        pvm = new PaletteViewModel(new Point(0,0),300,600,new WindowGameControllerFacade(gc));
        ProgramAreaViewModel pavm = new ProgramAreaViewModel(new Point(0,0),10,10,new WindowGameControllerFacade(gc));
        gc.setProgramAreaVM(pavm);
        ScrollBarViewModel sbvm = new ScrollBarViewModel(new Point(0,0),0,0);
        pvm.addScrollBar(sbvm);
        s = new StdBlockData(new Point(0,0),5,5,"test");
    }

    @Test
    void getAllButtons() {
        assertEquals(8,pvm.getAllButtons().size());
    }

    @Test
    void reactToBlockCreate() {
        assertEquals(8,pvm.getAllButtons().size());
        FunctionDefinitionBlockModel f = new FunctionDefinitionBlockModel(s);
        pvm.reactToBlockCreate(f);
        assertEquals(9,pvm.getAllButtons().size());
    }

    @Test
    void reactToBlockRemove() {
        FunctionDefinitionBlockModel f = new FunctionDefinitionBlockModel(s);
        pvm.reactToBlockCreate(f);
        assertEquals(9,pvm.getAllButtons().size());
        pvm.reactToBlockRemove(f);
        assertEquals(8,pvm.getAllButtons().size());
    }

    @Test
    void handleMousePress() {
        pvm.HandleMousePress(2,10);
        assertEquals(BlockModelType.ACTION,gc.getDraggedBlockVM().getModel().getBlockModelType());
    }

    @Test
    void setWidth() {
        pvm.setWidth(100);
        assertEquals(99,((BlockButtonViewModel)pvm.getAllButtons().toArray()[0]).getBGPolygon().getBounds().width);
    }
}