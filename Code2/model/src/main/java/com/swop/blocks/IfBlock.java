package com.swop.blocks;

import java.awt.*;

/**
 * A block that is an if-statement.
 */
public class IfBlock extends StatementBlock {
    /**
     * creates a block that is an if-statement with the given position, width and height.
     * @param position The given position of the ifBlock.
     * @param width The given width of the ifBlock.
     * @param height The given height of the ifBlock.
     */
    public IfBlock(Point position, int width, int height) {
        super(position, width, height);
    }

    /**
     * Sets busy to false and nextBodyBlock to null.
     */
    @Override
    protected void handleEndOfBody() {
        setBusy(false);
        setNextBodyBlock(null);
    }


}
