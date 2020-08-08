package com.swop;

import com.swop.blocks.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ProgramAreaViewModelTest {

    GameController gc;
    ProgramAreaViewModel pavm;
    StdBlockData s;
    GameWorld gw;

    @BeforeEach
    void setUp() {
        MyGameWorldType gwt = new MyGameWorldType();
        gw = gwt.createNewInstance();
        gc = new GameController(gwt);
        pavm = new ProgramAreaViewModel(new Point(0,0),300,600,new WindowGameControllerFacade(gc));
        s = new StdBlockData(new Point(0,0),5,5,"test");
        ScrollBarViewModel sbvm = new ScrollBarViewModel(new Point(0,0),0,0);
        pavm.addScrollBar(sbvm);
    }

    @Test
    void handleMousePress() {
        pavm.getModel().getAllBlocks().add(new ActionBlockModel(s,MyAction.MOVE_DOWN));
        pavm.HandleMousePress(4,4);
        assertTrue(pavm.getModel().getAllBlocks().isEmpty());
        assertNotNull(gc.getDraggedBlockVM());
    }

    @Test
    void dropBlock() {
        IfBlockModel parent = new IfBlockModel(s,30);
        pavm.DropBlock(parent);
        assertEquals(1,pavm.getAllBlocks().size());
        ActionBlockModel child = new ActionBlockModel(s,MyAction.MOVE_DOWN);
        child.setPosition(new Point(10,30));
        pavm.DropBlock(child);
        assertEquals(2,pavm.getAllBlocks().size());
        assertEquals(child,parent.getFirstBodyBlockModel());
        ActionBlockModel next = new ActionBlockModel(s,MyAction.MOVE_DOWN);
        next.setPosition(new Point(0,45));
        pavm.DropBlock(next);
        assertEquals(3,pavm.getAllBlocks().size());
        assertEquals(next,parent.getNext());
        ConditionBlockModel con = new ConditionBlockModel(s,false,null);
        con.setPosition(new Point(50,0));
        pavm.DropBlock(con);
        assertEquals(4,pavm.getAllBlocks().size());
    }

    @Test
    void removeBlock() {
        IfBlockModel parent = new IfBlockModel(s,30);
        pavm.DropBlock(parent);
        assertEquals(1,pavm.getAllBlocks().size());
        ActionBlockModel child = new ActionBlockModel(s,MyAction.MOVE_DOWN);
        child.setPosition(new Point(10,30));
        pavm.DropBlock(child);
        assertEquals(2,pavm.getAllBlocks().size());
        assertEquals(child,parent.getFirstBodyBlockModel());

        pavm.RemoveBlock(child);
        assertNull(parent.getFirstBodyBlockModel());
    }

    @Test
    void executeNext() {
        assertEquals(SuccessState.FAILURE,pavm.ExecuteNext(gw));
        pavm.getModel().getBlockProgram().add(new ActionBlockModel(s,MyAction.MOVE_DOWN));
        assertEquals(SuccessState.SUCCESS,pavm.ExecuteNext(gw));
    }

    @Test
    void setHighlight() {
        ActionBlockModel child = new ActionBlockModel(s,MyAction.MOVE_DOWN);
        pavm.getModel().getBlockProgram().add(child);
        Color normalColor = Color.RED;
        Color highlightColor = new Color(255,140,140);
        assertEquals(normalColor,child.getColor());
        pavm.setHighlight();
        assertEquals(highlightColor,child.getColor());
    }

    @Test
    void generateProgram() {
        ActionBlockModel first = new ActionBlockModel(s,MyAction.MOVE_DOWN);
        ActionBlockModel second = new ActionBlockModel(s,MyAction.MOVE_DOWN);
        ActionBlockModel third = new ActionBlockModel(s,MyAction.MOVE_DOWN);
        second.setPosition(new Point(0,5));
        third.setPosition(new Point(0,19));
        pavm.DropBlock(first);
        pavm.DropBlock(second);
        pavm.DropBlock(third);
        assertEquals(0,pavm.getModel().getBlockProgram().size());
        pavm.GenerateProgram();
        assertEquals(3,pavm.getModel().getBlockProgram().size());
        assertEquals(first,pavm.getModel().getBlockProgram().peek());
        assertEquals(third,pavm.getModel().getBlockProgram().peekLast());
    }
}