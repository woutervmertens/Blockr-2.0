package com.swop.blocks;

import java.awt.*;

/**
 * A block that is an if-statement.
 */
public class IfBlockModel extends StatementBlockModel {
    /**
     * creates a block that is an if-statement with the given position, width and height.
     */
    public IfBlockModel(StdBlockData data, int conditionWidth) {
        super(data,conditionWidth);
        blockModelType = BlockModelType.IF;
    }


}
