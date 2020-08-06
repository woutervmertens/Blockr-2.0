package com.swop.blocks;

import com.swop.*;
import com.swop.command.GameCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BlockVMTest {
    ActionBlockVM a;
    ConditionBlockVM c;
    FunctionCallBlockVM f;
    FunctionDefinitionBlockModel fd;
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
        c = new ConditionBlockVM(new ConditionBlockModel(s,false,null));
        fd = new FunctionDefinitionBlockModel(s);
        fd.setFirstBodyBlockModel(new ActionBlockModel(s,MyAction.MOVE_RIGHT));
        f = new FunctionCallBlockVM(new FunctionCallBlockModel(s,fd));
    }

    @Test
    void getModel() {
        assertEquals(BlockModelType.ACTION,a.getModel().blockModelType);
        assertEquals(BlockModelType.CONDITION,c.getModel().blockModelType);
        assertEquals(BlockModelType.FUNCCALL,f.getModel().blockModelType);
    }

    @Test
    void execute() {
        assertEquals(SuccessState.SUCCESS,a.Execute(gwvm.getGameWorld(),pavm.getModel()));
        assertEquals(null,c.Execute(gwvm.getGameWorld(),pavm.getModel()));
        assertEquals(SuccessState.SUCCESS,f.Execute(gwvm.getGameWorld(),pavm.getModel()));
    }

    @Test
    void getViewData() {
        BlockViewData d = a.getViewData();
        assertEquals(Color.red,d.getColor());
        assertEquals("test",d.getText());
        assertEquals(11,d.getTextPosition().x);
        assertEquals(21,d.getTextPosition().y);
        assertNotNull(d.getPolygon());
        assertEquals(5,a.getHeight());
        d = c.getViewData();
        assertEquals(Color.ORANGE,d.getColor());
        assertEquals("test",d.getText());
        assertEquals(11,d.getTextPosition().x);
        assertEquals(21,d.getTextPosition().y);
        assertNotNull(d.getPolygon());
        d = f.getViewData();
        assertEquals(Color.GRAY,d.getColor());
        assertEquals("test",d.getText());
        assertEquals(11,d.getTextPosition().x);
        assertEquals(21,d.getTextPosition().y);
        assertNotNull(d.getPolygon());
    }

    @Test
    void remove() {
        a.setNext(f.getModel());
        f.Remove(a.getModel());
        assertNull(a.getNext());
        a.setNext(f.getModel());
        a.Remove(null);
        assertTrue(f.getModel().isFirst());
    }

    @Test
    void getNextPosition() {
        assertEquals(1,a.getNextPosition().x);
        assertEquals(6,a.getNextPosition().y);
    }

    @Test
    void getConnectorOrNull() {
        assertNotNull(a.getConnectorOrNull(new Point(1,6),BlockModelType.ACTION));
        assertNull(a.getConnectorOrNull(new Point(20,6),BlockModelType.ACTION));
        assertNull(a.getConnectorOrNull(new Point(1,6),BlockModelType.CONDITION));
        assertEquals(ConnectorType.NEXT,a.getConnectorOrNull(new Point(1,6),BlockModelType.ACTION).getType());
        assertEquals(ConnectorType.NEXT,c.getConnectorOrNull(new Point(6,1),BlockModelType.CONDITION).getType());
    }
}