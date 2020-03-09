package Blocks;

import WorldElements.Square;
import WorldElements.Wall;

public class WallInFrontBlock extends ConditionBlock{
    @Override
    public boolean checkCondition(boolean result, Square squareInFront) {
        return (squareInFront instanceof Wall);
    }
}