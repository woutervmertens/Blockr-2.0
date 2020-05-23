package com.swop;

import com.swop.blocks.ActionBlock;
import com.swop.blocks.ConditionBlock;
import com.swop.blocks.WhileBlock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.*;

class ProgramAreaTest {

    private ProgramArea programArea;

    private Point aPosition,wPosition;
    private int aWidth;
    private int aHeight;
    private Action action;

    private ActionBlock actionBlock;
    private WhileBlock whileBlock;



    @BeforeEach
    void setUp() {
        programArea = new ProgramArea();
        aPosition = new Point(2, 2);
        wPosition = new Point(2,33);
        aWidth = 3;
        aHeight = 2;
        action = RobotAction.MOVE_FORWARD;
        actionBlock = new ActionBlock(aPosition, aWidth, aHeight, action);
        whileBlock = new WhileBlock(wPosition,aWidth,aHeight);
    }

    @Test
    void getAllBlocks() {
        assertTrue(programArea.getAllBlocks().isEmpty());
        programArea.dropBlock(actionBlock);
        assertEquals(1,programArea.getAllBlocks().size());
        assertTrue(programArea.getAllBlocks().contains(actionBlock));
    }

    @Test
    void getProgram() {
        assertNotEquals(null,programArea.getProgram());
    }

    @Test
    void dropBlockIn() {
        Point expected = new Point(4,10);
        programArea.dropBlockIn(actionBlock, expected);
        assertEquals(expected.x,actionBlock.getPosition().x, "The expected and actual x-value of the position of the actionBlock are different.");
        assertEquals(expected.y, actionBlock.getPosition().y, "The expected and actual y-value of the position of the actionBlock are different." );

        assertTrue(programArea.getAllBlocks().contains(actionBlock));
    }

    @Test
    void dropBlock() {
        programArea.dropBlock(whileBlock);
        actionBlock.setParentBlock(whileBlock);
        whileBlock.insertBodyBlockAtIndex(actionBlock,0);
        programArea.dropBlock(actionBlock);
        assertTrue(programArea.getAllBlocks().contains(actionBlock));
        ActionBlock actionBlock2 = new ActionBlock(new Point(aPosition.x,aPosition.y + 5), aWidth, aHeight, action);
        actionBlock2.setParentBlock(whileBlock);
        whileBlock.insertBodyBlockAtIndex(actionBlock2,1);
        programArea.dropBlock(actionBlock2);
        ActionBlock actionBlock3 = new ActionBlock(new Point(aPosition.x,aPosition.y + 30), aWidth, aHeight, action);
        actionBlock3.setParentBlock(whileBlock);
        whileBlock.insertBodyBlockAtIndex(actionBlock3,1);
        programArea.dropBlock(actionBlock3);
        assertEquals(4,programArea.getAllBlocks().size());
        ConditionBlock conditionBlock = new ConditionBlock(wPosition,false,aWidth,aHeight,null);
        whileBlock.addConditionBlock(conditionBlock);
        programArea.dropBlock(conditionBlock);
        assertEquals(5,programArea.getAllBlocks().size());
        ConditionBlock conditionBlock2 = new ConditionBlock(wPosition,true,aWidth,aHeight,RobotPredicate.WALL_IN_FRONT);
        whileBlock.addConditionBlock(conditionBlock2);
        programArea.dropBlock(conditionBlock2);
        assertEquals(6,programArea.getAllBlocks().size());
    }

    @Test
    void getNextProgramBlock() {
        assertEquals(null,programArea.getNextProgramBlock());
        programArea.dropBlock(actionBlock);
        assertEquals(actionBlock,programArea.getNextProgramBlock());
    }

    @Test
    void setNextProgramBlock() {
        programArea.setNextProgramBlock(actionBlock);
        assertEquals(actionBlock,programArea.getNextProgramBlock());
    }

    @Test
    void getBlockAt() {
        programArea.dropBlock(actionBlock);
        assertEquals(actionBlock,programArea.getBlockAt(aPosition.x + 1,aPosition.y + 1));
    }

    @Test
    void removeBlockFromPA() {
        programArea.dropBlock(actionBlock);
        programArea.removeBlockFromPA(actionBlock);
        assertTrue(programArea.getAllBlocks().isEmpty());
        programArea.dropBlock(whileBlock);
        programArea.dropBlock(actionBlock);
        whileBlock.insertBodyBlockAtIndex(actionBlock,0);
        programArea.removeBlockFromPA(actionBlock);
        assertFalse(programArea.getAllBlocks().contains(actionBlock));
    }

    @Test
    void resetProgramExecution() {
        programArea.setNextProgramBlock(whileBlock);
        programArea.dropBlock(actionBlock);
        programArea.resetProgramExecution();
        assertEquals(actionBlock,programArea.getNextProgramBlock());
    }
}