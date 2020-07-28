package com.swop.blocks;

import java.awt.*;

/**
 * A block that is a while-statement.
 */
public class WhileBlock extends StatementBlock {
    /**
     * Creates a block that is a while statement with the given position, width and height.
     * @param position The given position of the whileBlock.
     * @param width The given width of the whileBlock.
     * @param height The given height of the whileBlock.
     */
    public WhileBlock(Point position, int width, int height) {
        super(position, width, height);
    }

    protected void handleEndOfBody() {
        if (isConditionValid()) {
            setNextBodyBlock(bodyBlocks.get(0));
        } else {
            setBusy(false);
        }
    }
}
