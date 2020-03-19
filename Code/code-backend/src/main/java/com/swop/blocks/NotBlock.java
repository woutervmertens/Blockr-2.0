package com.swop.blocks;

import com.swop.worldElements.Square;

public class NotBlock extends ConditionBlock {
    @Override
    public boolean checkCondition(boolean result, Square squareInFront) {
        return !result;
    }
}
