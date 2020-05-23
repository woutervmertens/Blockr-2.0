package com.swop;

import com.swop.blocks.ActionBlock;
import com.swop.blocks.ConditionBlock;
import com.swop.blocks.WhileBlock;
import org.junit.jupiter.api.BeforeEach;
import com.swop.blocks.ConditionBlock;
import com.swop.blocks.IfBlock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ProgramAreaTest {

    private ProgramArea programArea;

    private Point aPosition,wPosition;
    private int aWidth;
    private int aHeight;
    private Action action;

    private ActionBlock actionBlock;
    private WhileBlock whileBlock;


    // move forward block
    private final Point mPosition = new Point(2, 2);
    private final int mWidth = 30;
    private final int mHeight = 20;
    private final Action mAction = RobotAction.MOVE_FORWARD;
    private final ActionBlock mActionBlock = new ActionBlock(mPosition, mWidth, mHeight, mAction);
    // turn left block
    private final Point tPosition = new Point(2, 4);
    private final int tWidth = 30;
    private final int tHeight = 20;
    private final Action tAction = RobotAction.TURN_LEFT;
    private final ActionBlock tActionBlock = new ActionBlock(tPosition, tWidth, tHeight, tAction);
    // if block
    private final Point ifPosition = new Point(2, 6);
    private final int ifWidth = 30;
    private final int ifHeight = 30;
    private final IfBlock ifBlock = new IfBlock(ifPosition, ifWidth, ifHeight);
    // condition block WIF
    private final Point conPosition = new Point(5, 6);
    private final boolean ispredicate = true;
    private final int conwidth = 30;
    private final int conHeight = 20;
    private final Predicate predicate = RobotPredicate.WALL_IN_FRONT;
    private final ConditionBlock WIF = new ConditionBlock(conPosition, ispredicate, conwidth, conHeight, predicate);
    // condition block NOT
    private final Point con2Position = new Point(5, 6);
    private final boolean ispredicate2 = false;
    private final int con2width = 30;
    private final int con2Height = 20;
    private final Predicate predicate2 = null;
    private final ConditionBlock notBlock = new ConditionBlock(con2Position, ispredicate2, con2width, con2Height, predicate2);
    // turn right block
    private final Point tRPosition = new Point(2, 4);
    private final int tRWidth = 30;
    private final int tRHeight = 20;
    private final Action tRAction = RobotAction.TURN_LEFT;
    private final ActionBlock tRActionBlock = new ActionBlock(tRPosition, tRWidth, tRHeight, tRAction);


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
        //move forward
        Point expected = new Point(4,10);
        programArea.dropBlockIn(mActionBlock, expected);
        assertEquals(expected.x,mActionBlock.getPosition().x, "The expected and actual x-value of the position of the actionBlock are different.");
        assertEquals(expected.y, mActionBlock.getPosition().y, "The expected and actual y-value of the position of the actionBlock are different." );

        assertTrue(programArea.getAllBlocks().contains(mActionBlock));
        // turn left
        Point tExpected = new Point(4,33);
        programArea.dropBlockIn(tActionBlock, tExpected);
        assertEquals(tExpected.x,tActionBlock.getPosition().x, "The expected and actual x-value of the position of the actionBlock are different.");
        assertEquals(tExpected.y, tActionBlock.getPosition().y, "The expected and actual y-value of the position of the actionBlock are different." );

        assertTrue(programArea.getAllBlocks().contains(tActionBlock));
        // if
        Point ifExpected = new Point(4,56);
        programArea.dropBlockIn(ifBlock, ifExpected);
        assertEquals(ifExpected.x,ifBlock.getPosition().x, "The expected and actual x-value of the position of the ifBlock are different.");
        assertEquals(ifExpected.y, ifBlock.getPosition().y, "The expected and actual y-value of the position of the ifBlock are different." );

        assertTrue(programArea.getAllBlocks().contains(ifBlock));
        // condition NOT
        Point con2Expected = new Point(24,56);
        programArea.dropBlockIn(notBlock, con2Expected);
        assertEquals(con2Expected.x, notBlock.getPosition().x, "The expected and actual x-value of the position of the conditionBlock are different.");
        assertEquals(con2Expected.y, notBlock.getPosition().y, "The expected and actual y-value of the position of the ConditionBlock are different." );

        assertTrue(programArea.getAllBlocks().contains(notBlock));
        assertTrue(ifBlock.getConditions().contains(notBlock));
        // condition WIF
        Point conExpected = new Point(57,56);
        programArea.dropBlockIn(WIF, conExpected);
        assertEquals(conExpected.x, WIF.getPosition().x, "The expected and actual x-value of the position of the conditionBlock are different.");
        assertEquals(conExpected.y, WIF.getPosition().y, "The expected and actual y-value of the position of the ConditionBlock are different." );

        assertTrue(programArea.getAllBlocks().contains(WIF));
        assertTrue(ifBlock.getConditions().contains(WIF));
        // turn right body
        Point tRExpected = new Point(14,81);
        programArea.dropBlockIn(tRActionBlock, tRExpected);
        assertEquals(tRExpected.x,tRActionBlock.getPosition().x, "The expected and actual x-value of the position of the actionBlock are different.");
        assertEquals(tRExpected.y, tRActionBlock.getPosition().y, "The expected and actual y-value of the position of the actionBlock are different." );

        assertTrue(programArea.getAllBlocks().contains(tRActionBlock));
        assertTrue(ifBlock.getBodyBlocks().contains(tRActionBlock));


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