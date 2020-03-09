package blocks;

import worldElements.Square;

public class WallInFrontBlock extends ConditionBlock{
    @Override
    public boolean checkCondition(boolean result, Square squareInFront) {
        return (squareInFront.isPassable());
    }
}