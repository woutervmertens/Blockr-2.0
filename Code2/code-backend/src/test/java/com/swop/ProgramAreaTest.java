package com.swop;

import com.swop.Action;
import com.swop.ProgramArea;
import com.swop.blocks.ActionBlock;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ProgramAreaTest {

    private ProgramArea programArea = ProgramArea.getInstance();

    private Point aPosition = new Point(2,2);
    private int aWidth = 3;
    private int aHeight = 2;
    private Action action = Action.MOVE_FORWARD;

    private ActionBlock actionBlock = new ActionBlock(aPosition,aWidth,aHeight,action);


    @Test
    void dropBlockIn() {
        Point expected = new Point(4,10);
        programArea.dropBlockIn(actionBlock, expected);
        assertEquals(expected.x,actionBlock.getPosition().x, "The expected and actual x-value of the position of the actionBlock are different.");
        assertEquals(expected.y, actionBlock.getPosition().y, "The expected and actual y-value of the position of the actionBlock are different." );

        assertTrue(programArea.getAllBlocks().contains(actionBlock));
    }



    @Test
    void getConnectionPoint() {
    }

    @Test
    void removeBlockFromPA() {
    }

    @Test
    void resetProgramExecution() {
    }
}