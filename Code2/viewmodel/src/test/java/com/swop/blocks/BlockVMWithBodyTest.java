package com.swop.blocks;

import com.swop.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BlockVMWithBodyTest {
    ActionBlockVM a;
    FunctionDefinitionBlockVM f;
    GameController gameController;
    ProgramAreaViewModel pavm;
    GameWorldViewModel gwvm;
    StdBlockData s;

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
        s = new StdBlockData(new Point(1,1),5,5,"test");
        a = new ActionBlockVM(new ActionBlockModel(s,MyAction.MOVE_DOWN));
        f = new FunctionDefinitionBlockVM(new FunctionDefinitionBlockModel(s));
        f.setFirstBodyBlock(a.getModel());
    }

    @Test
    void remove() {
        f.Remove(null);
        assertNull(f.getFirstBodyBlock());
    }

    @Test
    void replaceChild() {
        f.replaceChild(a.getModel());
        assertNull(f.getFirstBodyBlock());
    }

    @Test
    void updatePosition() {
        f.updatePosition(new Point(2,2));
        assertEquals(2,f.getPosition().x);
        assertEquals(1,a.getPosition().x);
    }

    @Test
    void getConnectorOrNull() {
        assertNotNull(f.getConnectorOrNull(new Point(11,31),BlockModelType.ACTION));
        assertNull(f.getConnectorOrNull(new Point(120,6),BlockModelType.ACTION));
        assertNull(f.getConnectorOrNull(new Point(11,31),BlockModelType.CONDITION));
    }

    @Test
    void execute(){
        f.Execute(gwvm.getGameWorld(), pavm.getModel());
        assertEquals(1,pavm.getModel().getBlockProgram().size());
    }

    @Test
    void getViewData() {
        BlockViewData d = f.getViewData();
        assertEquals(Color.white,d.getColor());
        assertEquals("test",d.getText());
        assertEquals(11,d.getTextPosition().x);
        assertEquals(21,d.getTextPosition().y);
        assertNotNull(d.getPolygon());
        assertEquals(40,f.getHeight());
    }
}