package com.swop.blocks;

import com.swop.Action;
import com.swop.BlockrGame;
import com.swop.RobotAction;
import com.swop.RobotGameWorldType;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FunctionCallBlockTest {
    private final Point position = new Point(5, 1);
    private final int width = 3;
    private final int height = 4;
    private final FunctionDefinitionBlock definition = new FunctionDefinitionBlock(position,width,height);
    private ActionBlock action, action2;
    private BlockrGame blockrGame = new BlockrGame(10, new RobotGameWorldType());

    private final FunctionCallBlock callBlock = new FunctionCallBlock(position, width, height, definition);

    @Test
    void execute() {
        action = new ActionBlock(position,width,height,RobotAction.TURN_RIGHT);
        action2 = new ActionBlock(position,width,height,RobotAction.TURN_RIGHT);
        action.setBlockrGame(blockrGame);
        action2.setBlockrGame(blockrGame);
        callBlock.execute();
        assertFalse(callBlock.isBusy());
        definition.insertBodyBlockAtIndex(action,0);
        definition.insertBodyBlockAtIndex(action2,1);
        callBlock.execute();
        assertTrue(callBlock.isBusy());
    }

    @Test
    void getPlugPosition() {
        Point expectedPosition = new Point(position.x, position.y + callBlock.getHeight() + callBlock.getStep());
        assertEquals(expectedPosition.x, callBlock.getPlugPosition().x, "Expected x-position and actual x-position of plug are different");
        assertEquals(expectedPosition.y, callBlock.getPlugPosition().y, "Expected y-position and actual y-position of plug are different");
    }

    @Test
    void getSocketPosition() {
        Point expectedPosition = new Point(position.x, position.y + callBlock.getStep());
        assertEquals(expectedPosition.x, callBlock.getSocketPosition().x, "Expected x-position and actual x-position of socket are different");
        assertEquals(expectedPosition.y, callBlock.getSocketPosition().y, "Expected y-position and actual y-position of socket are different");
    }
}