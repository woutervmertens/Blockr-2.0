package Blocks;

import WorldElements.Square;

public class NotBlock extends ConditionBlock {
    @Override
    public boolean checkCondition(boolean result, Square squareInFront) {
        return !result;
    }
}
