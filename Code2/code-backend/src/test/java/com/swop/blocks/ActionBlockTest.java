package com.swop.blocks;

import com.swop.Action;
import com.swop.RobotAction;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionBlockTest {

    private final Point position = new Point(5, 1);
    private final int width = 3;
    private final int height = 4;
    private final Action action = RobotAction.MOVE_FORWARD;

    private final ActionBlock actionBlock = new ActionBlock(position, width, height, action);

    @Test
    void getPlugPosition() {
        Point expectedPosition = new Point(position.x, position.y + actionBlock.getHeight() + actionBlock.getStep());
        assertEquals(expectedPosition.x, actionBlock.getPlugPosition().x, "Expected x-position and actual x-position of plug are different");
        assertEquals(expectedPosition.y, actionBlock.getPlugPosition().y, "Expected y-position and actual y-position of plug are different");
    }

    @Test
    void getSocketPosition() {
        Point expectedPosition = new Point(position.x, position.y + actionBlock.getStep());
        assertEquals(expectedPosition.x, actionBlock.getSocketPosition().x, "Expected x-position and actual x-position of socket are different");
        assertEquals(expectedPosition.y, actionBlock.getSocketPosition().y, "Expected y-position and actual y-position of socket are different");
    }


}