package com.swop.blocks;

import com.swop.Predicate;
import com.swop.RobotPredicate;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConditionBlockTest {
    private Point position = new Point(1, 1);
    private Predicate predicate = RobotPredicate.WALL_IN_FRONT;
    private int width = 3;
    private int height = 2;

    private ConditionBlock conditionBlock = new ConditionBlock(position,true, width, height, predicate);

    @Test
    void getPlugPosition() {
        Point expectedPosition = new Point(position.x + width + conditionBlock.getStep(), position.y);
        assertEquals(expectedPosition.x, conditionBlock.getPlugPosition().x, "expected and actual x-value are different");
        assertEquals(expectedPosition.y, conditionBlock.getPlugPosition().y, "expected and actual y-value are different");
    }

    @Test
    void getSocketPosition() {
        Point expectedPosition = new Point(position.x + conditionBlock.getStep(), position.y);
        assertEquals(expectedPosition.x, conditionBlock.getSocketPosition().x, "expected and actual x-value are different");
        assertEquals(expectedPosition.y, conditionBlock.getSocketPosition().y, "expected and actual y-value are different");
    }
}