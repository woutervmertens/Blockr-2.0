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

    @Override
    public BlockModel clone() {
        WhileBlockModel cs = new WhileBlockModel(new StdBlockData((Point) getPosition().clone(),getWidth(),height,getText()),getConditionWidth());
        cs.setHighlightState(isHighlight);
        cs.setNextBlock(getNext());
        cs.setGapSize(getGapSize());
        cs.setFirstBodyBlockModel(getFirstBodyBlockModel());
        cs.setFirstCondition(getFirstCondition());
        cs.setIsFirstFlag(isFirst());
        return cs;
    }

}
