package com.swop.blocks;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ConditionBlockTest {
    private Point position = new Point(1,1);
    private boolean predicate = true;
    private int width = 3;
    private int height = 2;

    private ConditionBlock conditionBlock = new ConditionBlock(position,predicate,width, height);

    @Test
    void getPlugPosition() {
        Point expectedPosition = new Point(position.x + width + conditionBlock.getStep(), position.y);
        assertEquals(expectedPosition.x, conditionBlock.getPlugPosition().x, "expected and actual x-value are different");
        assertEquals(expectedPosition.y, conditionBlock.getPlugPosition().y, "expected and actual y-value are different");
    }

    @Test
    void getSocketPosition()
        {Point expectedPosition = new Point(position.x + conditionBlock.getStep(), position.y);
        assertEquals(expectedPosition.x, conditionBlock.getSocketPosition().x, "expected and actual x-value are different");
        assertEquals(expectedPosition.y, conditionBlock.getSocketPosition().y, "expected and actual y-value are different");
    }
}