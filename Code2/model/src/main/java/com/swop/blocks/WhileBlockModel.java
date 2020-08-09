package com.swop.blocks;

import java.awt.*;

/**
 * A block that is a while-statement.
 */
public class WhileBlockModel extends StatementBlockModel {
    /**
     * Creates a block that is a while statement with the given position, width and height.
     *
     * @param data standard data container for the Block
     * @param conditionWidth the width of a condition Block
     */
    public WhileBlockModel(StdBlockData data, int conditionWidth) {
        super(data, conditionWidth);
        blockModelType = BlockModelType.WHILE;
    }

    /**
     * Clones the object.
     * @return A copy of this WhileBlockModel object.
     */
    @Override
    public BlockModel clone() {
        WhileBlockModel cs = new WhileBlockModel(new StdBlockData(new Point(getPosition().x,getPosition().y),getWidth(),height,getText()),getConditionWidth());
        cs.setHighlightState(isHighlight);
        cs.setNextBlock(getNext());
        cs.setGapSize(getGapSize());
        cs.setFirstBodyBlockModel(getFirstBodyBlockModel());
        cs.setFirstCondition(getFirstCondition());
        cs.setIsFirstFlag(isFirst());
        return cs;
    }

}
