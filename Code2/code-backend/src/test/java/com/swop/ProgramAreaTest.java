package com.swop;

import com.swop.blocks.ActionBlock;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.*;

class ProgramAreaTest {

    private final ProgramArea programArea = new ProgramArea();

    private final Point aPosition = new Point(2, 2);
    private final int aWidth = 3;
    private final int aHeight = 2;
    private final Action action = RobotAction.MOVE_FORWARD;

    private final ActionBlock actionBlock = new ActionBlock(aPosition, aWidth, aHeight, action);

    @Test
    void dropBlockIn() {
        Point expected = new Point(4,10);
        programArea.dropBlockIn(actionBlock, expected);
        assertEquals(expected.x,actionBlock.getPosition().x, "The expected and actual x-value of the position of the actionBlock are different.");
        assertEquals(expected.y, actionBlock.getPosition().y, "The expected and actual y-value of the position of the actionBlock are different." );

        assertTrue(programArea.getAllBlocks().contains(actionBlock));
    }

}