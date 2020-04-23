package com.swop.blocks;

import com.swop.Action;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    private Point position = new Point(4, 9);
    private int width = 9;
    private int height = 4;

    private Action action = Action.MOVE_FORWARD;
    private ExecuteType executeType = ExecuteType.WorldChanging;

    private ActionBlock block = new ActionBlock(position, width, height, action);

    private Point position1 = new Point(4, 2);
    private int width1 = 9;
    private int height1 = 4;

    private IfBlock ifBlock = new IfBlock(position1,width1,height1);


    @Test
    void getStep() {
        int expectedStep = height/6;
        assertEquals(expectedStep, block.getStep(), "expected and actual step are different");
    }

    @Test
    void getWidth() {
        assertEquals(width, block.getWidth(),"expected and actual width are different");
    }

    @Test
    void getHeight() {
        assertEquals(height, block.getHeight(),"expected and actual height are different");
    }

    @Test
    void getExecuteType() {
        assertEquals(executeType, block.getExecuteType(),"expected and actual executeType are different" );
    }

    @Test
    void getParentStatementIsNull() {
        assertNull(block.getParentStatement(), "actual parentStatement isn't null");
    }

    @Test
    void setParentStatement() {
        block.setParentStatement(ifBlock);
        assertEquals(ifBlock, block.getParentStatement(),"expected and actual parentStatement are different" );
    }

    @Test
    void isPositionOnTrue() {
        assertTrue(block.isPositionOn(5, 10) );
    }

    @Test
    void isPositionOnFalse() {
        assertFalse(block.isPositionOn(3, 5));
    }

    @Test
    void isUnderTrue() {
        assertTrue(block.isUnder(ifBlock));
    }

    @Test
    void isUnderFalse() {
        assertFalse(ifBlock.isUnder(block));
    }

    @Test
    void getPosition() {
        assertEquals(position.x, block.getPosition().x, "expected and actual x-value are different");
        assertEquals(position.y, block.getPosition().y, "expected and actual y-value are different");
    }

    @Test
    void setPosition() {
        Point expectedPosition = new Point(1,1);
        block.setPosition(expectedPosition);
        assertEquals(expectedPosition.x, block.getPosition().x, "expected and actual x-value are different");
        assertEquals(expectedPosition.y, block.getPosition().y, "expected and actual y-value are different");
    }
}