package com.swop;

import com.swop.blocks.ActionBlock;
import com.swop.blocks.ConditionBlock;
import com.swop.blocks.IfBlock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ProgramAreaTest {

    private ProgramArea programArea = new ProgramArea();
// move forward block
    private final Point mPosition = new Point(2, 2);
    private final int mWidth = 30;
    private final int mHeight = 20;
    private final Action mAction = RobotAction.MOVE_FORWARD;
    private final ActionBlock actionBlock = new ActionBlock(mPosition, mWidth, mHeight, mAction);
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
    void reset(){
        programArea = new ProgramArea();
    }

    @Test
    void dropBlockIn() {
        //move forward
        Point expected = new Point(4,10);
        programArea.dropBlockIn(actionBlock, expected);
        assertEquals(expected.x,actionBlock.getPosition().x, "The expected and actual x-value of the position of the actionBlock are different.");
        assertEquals(expected.y, actionBlock.getPosition().y, "The expected and actual y-value of the position of the actionBlock are different." );

        assertTrue(programArea.getAllBlocks().contains(actionBlock));
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

        programArea.removeBlockFromPA(actionBlock);
        assertFalse(programArea.getAllBlocks().contains(actionBlock));
        //move forward
        expected = new Point(4, 33);
        programArea.dropBlockIn(actionBlock, expected);
        assertEquals(expected.x,actionBlock.getPosition().x, "The expected and actual x-value of the position of the actionBlock are different.");
        assertEquals(expected.y, actionBlock.getPosition().y, "The expected and actual y-value of the position of the actionBlock are different." );

    }


    @Test
    void getNextProgramBlock() {

    }

    @Test
    void setNextProgramBlock() {
    }

    @Test
    void testSetNextProgramBlock() {
    }

    @Test
    void getBlockAt() {
    }

    @Test
    void removeBlockFromPA() {
    }

    @Test
    void resetProgramExecution() {
    }
}