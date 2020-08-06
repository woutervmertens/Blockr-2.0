package com.swop.blocks;

import com.swop.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class StatementBlockVMTest {

    ActionBlockVM a;
    IfBlockVM i;
    WhileBlockVM w;
    ConditionBlockVM c, n;
    GameController gameController;
    ProgramAreaViewModel pavm;
    GameWorldViewModel gwvm;
    StdBlockData s;

    @BeforeEach
    void setUp() {
        gameController = new GameController(new RobotGameWorldType());
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
        a = new ActionBlockVM(new ActionBlockModel(s,RobotAction.TURN_LEFT));
        c = new ConditionBlockVM(new ConditionBlockModel(s,true,RobotPredicate.WALL_IN_FRONT));
        n = new ConditionBlockVM(new ConditionBlockModel(s,false,null));
        i = new IfBlockVM(new IfBlockModel(s,c.getModel().getWidth()));
        w = new WhileBlockVM(new WhileBlockModel(s,c.getModel().getWidth()));
    }

    @Test
    void isConditionValid() {
        i.setFirstCondition(c.getModel());
        assertFalse(i.isConditionValid(gwvm.getGameWorld()));
        n.setNext(c.getModel());
        i.setFirstCondition(n.getModel());
        assertTrue(i.isConditionValid(gwvm.getGameWorld()));
    }

    @Test
    void remove() {
        i.setFirstCondition(c.getModel());
        i.Remove(null);
        assertNull(i.getFirstCondition());
    }

    @Test
    void replaceChild() {
        i.setFirstCondition(c.getModel());
        i.replaceChild(c.getModel());
        assertNull(i.getFirstCondition());
        n.setNext(c.getModel());
        i.setFirstCondition(n.getModel());
        i.replaceChild(n.getModel());
        assertEquals(c.getModel(),i.getFirstCondition());
    }

    @Test
    void getConnectorOrNull() {
        assertNotNull(i.getConnectorOrNull(new Point(-14,1),BlockModelType.CONDITION));
        assertNull(i.getConnectorOrNull(new Point(120,6),BlockModelType.CONDITION));
        assertNull(i.getConnectorOrNull(new Point(-14,1),BlockModelType.ACTION));
    }

    @Test
    void execute(){
        assertEquals(SuccessState.SUCCESS,i.Execute(gwvm.getGameWorld(), pavm.getModel()));
        assertEquals(SuccessState.SUCCESS,w.Execute(gwvm.getGameWorld(), pavm.getModel()));
        n.setNext(c.getModel());
        i.setFirstCondition(n.getModel());
        i.setFirstBodyBlock(a.getModel());
        i.Execute(gwvm.getGameWorld(), pavm.getModel());
        assertEquals(1,pavm.getModel().getBlockProgram().size());
        w.setFirstCondition(n.getModel());
        w.setFirstBodyBlock(a.getModel());
        w.Execute(gwvm.getGameWorld(), pavm.getModel());
        assertEquals(3,pavm.getModel().getBlockProgram().size()); //actionblock + while + actionblock from if
    }

    @Test
    void getViewData() {
        BlockViewData d = i.getViewData();
        assertEquals(Color.cyan,d.getColor());
        assertEquals("test",d.getText());
        assertEquals(11,d.getTextPosition().x);
        assertEquals(21,d.getTextPosition().y);
        assertNotNull(d.getPolygon());
        assertEquals(40,i.getHeight());
        d = w.getViewData();
        assertEquals(Color.cyan,d.getColor());
        assertEquals("test",d.getText());
        assertEquals(11,d.getTextPosition().x);
        assertEquals(21,d.getTextPosition().y);
        assertNotNull(d.getPolygon());
        assertEquals(40,w.getHeight());
    }
}