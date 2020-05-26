package com.swop.blocks;

import com.swop.Action;
import com.swop.BlockrGame;
import com.swop.RobotAction;
import com.swop.RobotGameWorldType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    private Point position;
    private int width;
    private int height;

    private Action action;
    private ExecuteType executeType;

    private ActionBlock block;

    private Point position1;
    private int width1;
    private int height1;

    private IfBlock ifBlock;
    BlockrGame blockrGame;

    @BeforeEach
    void setUp(){
        blockrGame = new BlockrGame(10,new RobotGameWorldType());
        position = new Point(4, 9);
        width = 9;
        height = 4;
        action = RobotAction.MOVE_FORWARD;
        executeType = ExecuteType.WorldChanging;
        block = new ActionBlock(position, width, height, action);
        block.setBlockrGame(blockrGame);
        position1 = new Point(4, 2);
        width1 = 9;
        height1 = 4;
        ifBlock = new IfBlock(position1, width1, height1);
        ifBlock.setBlockrGame(blockrGame);
    }

    @Test
    void testClone() {
        Block a = block.clone();
        assertEquals(position,a.getPosition());
    }

    @Test
    void setBlockrGame() {
        BlockrGame b = new BlockrGame(9,new RobotGameWorldType());
        block.setBlockrGame(b);
        assertNotEquals(ifBlock.getGameWorld(),block.getGameWorld());
        assertEquals(b.getGameWorld(),block.getGameWorld());
    }

    @Test
    void testGetStep() {
        assertNotEquals(height,block.getStep());
        assertEquals(height/6,block.getStep());
    }

    @Test
    void testGetWidth() {
        assertNotEquals(height,block.getWidth());
        assertEquals(width,block.getWidth());
    }

    @Test
    void testGetHeight() {
        assertNotEquals(width,block.getHeight());
        assertEquals(height,block.getHeight());
    }

    @Test
    void isBusy() {
        assertFalse(block.isBusy());
    }

    @Test
    void setBusy() {
        block.setBusy(true);
        assertTrue(block.isBusy());
    }

    @Test
    void testGetExecuteType() {
        assertEquals(ExecuteType.WorldChanging,block.getExecuteType());
        assertEquals(ExecuteType.NonWorldChanging,ifBlock.getExecuteType());
        assertNotEquals(ExecuteType.NonExecutable,block.getExecuteType());
    }

    @Test
    void getParentBlock() {
        assertEquals(null,block.getParentBlock());
    }

    @Test
    void setParentBlock() {
        block.setParentBlock(ifBlock);
        assertEquals(ifBlock,block.getParentBlock());
    }

    @Test
    void isPositionOn() {
        assertTrue(block.isPositionOn(5, 10));
        assertFalse(block.isPositionOn(3, 5));
    }

    @Test
    void isUnder() {
        assertTrue(block.isUnder(ifBlock));
        assertFalse(ifBlock.isUnder(block));
    }

    @Test
    void getPosition() {
        assertEquals(position.x, block.getPosition().x, "expected and actual x-value are different");
        assertEquals(position.y, block.getPosition().y, "expected and actual y-value are different");
    }

    @Test
    void setPosition() {
        Point expectedPosition = new Point(1, 1);
        block.setPosition(expectedPosition);
        assertEquals(expectedPosition.x, block.getPosition().x, "expected and actual x-value are different");
        assertEquals(expectedPosition.y, block.getPosition().y, "expected and actual y-value are different");
    }

    @Test
    void getPreviousDropPosition() {
        assertEquals(null,block.getPreviousDropPosition());
    }

    @Test
    void setPreviousDropPosition() {
        block.setPreviousDropPosition(new Point(78,89));
        assertEquals(78,block.getPreviousDropPosition().x);
        assertEquals(89,block.getPreviousDropPosition().y);
    }

    @Test
    void getGameWorld() {
        assertEquals(blockrGame.getGameWorld(),block.getGameWorld());
    }

    @Test
    void getCount() {
        assertEquals(1,block.getCount());
    }
}