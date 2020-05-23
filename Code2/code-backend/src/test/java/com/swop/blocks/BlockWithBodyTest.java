package com.swop.blocks;

import com.swop.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BlockWithBodyTest {
    FunctionDefinitionBlock block;
    Point position;
    ActionBlock action, action2;
    BlockrGame blockrGame;


    @BeforeEach
    void setUp(){
        position = new Point(10,10);
        block = new FunctionDefinitionBlock(position,1,1);
        action = new ActionBlock(position,1,1, RobotAction.TURN_LEFT);
        action2 = new ActionBlock(position,1,1, RobotAction.TURN_RIGHT);
        blockrGame = new BlockrGame(10,new RobotGameWorldType());
        action.setBlockrGame(blockrGame);
    }

    @Test
    void setPosition() {
        block.getBodyBlocks().add(action);
        block.getBodyBlocks().add(action2);
        Point newPos = new Point(99,99);
        block.setPosition(newPos);
        assertEquals(newPos,block.getPosition());
        assertEquals(newPos,block.getBodyBlocks().get(0).getPosition());
        assertEquals(newPos,block.getBodyBlocks().get(1).getPosition());
    }

    @Test
    void addBodyBlockAfter() {
        assertThrows(IllegalArgumentException.class, () -> block.addBodyBlockAfter(action,null));
        assertThrows(IllegalArgumentException.class, () -> block.addBodyBlockAfter(action,action2));
        block.getBodyBlocks().add(action);
        block.addBodyBlockAfter(action2,action);
        assertEquals(action,block.getBodyBlocks().get(0));
        assertEquals(action2,block.getBodyBlocks().get(1));
    }

    @Test
    void addBodyBlockBefore() {
        assertThrows(IllegalArgumentException.class, () -> block.addBodyBlockBefore(action,null));
        assertThrows(IllegalArgumentException.class, () -> block.addBodyBlockBefore(action,action2));
        block.getBodyBlocks().add(action2);
        block.addBodyBlockBefore(action,action2);
        assertEquals(action,block.getBodyBlocks().get(0));
        assertEquals(action2,block.getBodyBlocks().get(1));
    }

    @Test
    void insertBodyBlockAtIndex() {
        block.insertBodyBlockAtIndex(action2,0);
        assertEquals(action2,block.getBodyBlocks().get(0));
        block.insertBodyBlockAtIndex(action,0);
        assertEquals(action,block.getBodyBlocks().get(0));
    }

    @Test
    void removeBodyBlock() {
        block.getBodyBlocks().add(action);
        block.removeBodyBlock(action);
        assertTrue(block.getBodyBlocks().isEmpty());
    }

    @Test
    void resetExecution() {
        block.setBusy(true);
        block.resetExecution();
        assertFalse(block.isBusy());
    }

    @Test
    void getNextBodyBlock() {
        assertEquals(null,block.getNextBodyBlock());
    }

    @Test
    void setNextBodyBlock() {
        block.insertBodyBlockAtIndex(action,0);
        block.insertBodyBlockAtIndex(action2,1);
        assertEquals(null,block.getNextBodyBlock());
        block.setNextBodyBlock();
        assertEquals(action,block.getNextBodyBlock());
        block.setNextBodyBlock();
        assertEquals(action2,block.getNextBodyBlock());
    }

    @Test
    void executeNextBodyBlock() {
        block.setBusy(true);
        block.executeNextBodyBlock();
        assertFalse(block.isBusy());

        block.insertBodyBlockAtIndex(action,0);
        block.setNextBodyBlock();
        block.executeNextBodyBlock();
        assertEquals(Direction.UP,((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getDirection());

    }

    @Test
    void getBodyBlocks() {
        assertEquals(block.bodyBlocks,block.getBodyBlocks());
    }

    @Test
    void getBodyPlugPosition() {
        assertEquals(20,block.getBodyPlugPosition().x);
        assertEquals(11,block.getBodyPlugPosition().y);
    }

    @Test
    void getGapSize() {
        assertEquals(0,block.getGapSize());
    }

    @Test
    void setGapSize() {
        block.setGapSize(7);
        assertEquals(7,block.getGapSize());
    }

    @Test
    void increaseGapSize() {
        block.setGapSize(1);
        assertEquals(1,block.getGapSize());
        block.increaseGapSize(2);
        assertEquals(3,block.getGapSize());
    }

    @Test
    void getCount() {
        assertEquals(1,block.getCount());
        block.getBodyBlocks().add(action);
        assertEquals(2,block.getCount());
    }
}