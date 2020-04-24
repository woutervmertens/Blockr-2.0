package com.swop.blocks;

import com.swop.Action;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionBlockTest {

    private Point position = new Point(5, 1);
    private int width = 3;
    private int height = 4;
    private Action action = Action.MOVE_FORWARD;

    private ActionBlock actionBlock = new ActionBlock(position, width, height, action);

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