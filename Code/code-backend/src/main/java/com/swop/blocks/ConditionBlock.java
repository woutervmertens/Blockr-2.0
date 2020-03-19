package com.swop.blocks;

import com.swop.worldElements.Square;

public abstract class ConditionBlock extends Block {
    public boolean checkCondition(boolean result, Square squareInFront) {
        return result;
    }

}
