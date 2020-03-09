package Blocks;

import WorldElements.Square;

public abstract class ConditionBlock extends Block {
    public boolean checkCondition(boolean result, Square squareInFront){return result;}

}
