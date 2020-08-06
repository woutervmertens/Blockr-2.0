package com.swop.blocks;

import java.awt.*;

/**
 * A block that is an if-statement.
 */
public class IfBlockModel extends StatementBlockModel {
    /**
     * creates a block that is an if-statement with the given position, width and height.
     *
     * @param data standard data container for the Block
     * @param conditionWidth the width of a condition Block
     */
    public IfBlockModel(StdBlockData data, int conditionWidth) {
        super(data,conditionWidth);
        blockModelType = BlockModelType.IF;
    }

    /**
     * Clones the object.
     * @return A copy of this IfBlockModel object.
     */
    @Override
    public BlockModel clone() {
        IfBlockModel cs = new IfBlockModel(new StdBlockData((Point) getPosition().clone(),getWidth(),height,getText()),getConditionWidth());
        cs.setHighlightState(isHighlight);
        cs.setNextBlock(getNext());
        cs.setGapSize(getGapSize());
        cs.setFirstBodyBlockModel(getFirstBodyBlockModel());
        cs.setFirstCondition(getFirstCondition());
        cs.setIsFirstFlag(isFirst());
        return cs;
    }
}
