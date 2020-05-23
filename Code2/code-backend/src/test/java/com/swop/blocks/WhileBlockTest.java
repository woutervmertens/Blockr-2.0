package com.swop.blocks;

import com.swop.BlockrGame;
import com.swop.RobotAction;
import com.swop.RobotGameWorldType;
import com.swop.RobotPredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WhileBlockTest {
    WhileBlock block;
    ConditionBlock condition;
    ActionBlock action, action2;
    BlockrGame blockrGame;
    Point position;
    int width, height;
    @BeforeEach
    void setUp(){
        position = new Point(1,1);
        width = 1;
        height = 1;
        block = new WhileBlock(position,width,height);
        condition = new ConditionBlock(position,true,width,height, RobotPredicate.WALL_IN_FRONT);
        action = new ActionBlock(position,width,height, RobotAction.TURN_RIGHT);
        action2 = new ActionBlock(position,width,height, RobotAction.TURN_LEFT);
        blockrGame = new BlockrGame(10,new RobotGameWorldType());
        block.setBlockrGame(blockrGame);
        action.setBlockrGame(blockrGame);
        action2.setBlockrGame(blockrGame);
        condition.setBlockrGame(blockrGame);
    }
    @Test
    void handleEndOfBody() {
        block.handleEndOfBody();
        assertFalse(block.isBusy());
        block.addConditionBlock(condition);
        block.insertBodyBlockAtIndex(action,0);
        block.insertBodyBlockAtIndex(action2,1);
        block.setNextBodyBlock(block.getBodyBlocks().get(1));
        action2.execute();
        block.handleEndOfBody();
        assertEquals(block.getBodyBlocks().get(0),block.getNextBodyBlock());
    }
}