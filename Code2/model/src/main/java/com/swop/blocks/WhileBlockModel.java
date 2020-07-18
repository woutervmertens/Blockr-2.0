package com.swop.blocks;

import java.awt.*;

/**
 * A block that is a while-statement.
 */
public class WhileBlockModel extends StatementBlockModel {
    /**
     * Creates a block that is a while statement with the given position, width and height.
     */
    public WhileBlockModel(StdBlockData data, int conditionWidth) {
        super(data, conditionWidth);
    }

}
