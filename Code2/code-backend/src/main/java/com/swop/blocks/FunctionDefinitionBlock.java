package com.swop.blocks;

import java.awt.*;

/**
 * A block that defines a function.
 */
public class FunctionDefinitionBlock extends BlockWithBody implements Executable{
    /**
     * Creates a block that defines a function with the given position, width and height.
     * @param position The given position of the functionDefinitionBlock.
     * @param width The given width of the functionDefinitionBlock.
     * @param height The given height of the functionDefinitionBlock.
     */
    public FunctionDefinitionBlock(Point position, int width, int height) {
        super(position, width, height);
        executeType = ExecuteType.NonWorldChanging;
    }

    /**
     * Sets busy to false.
     */
    @Override
    protected void handleEndOfBody() {
        setBusy(false);
    }

    // TODO: make a terminate method for all blocks, and when you terminate this block all call blocks should be gone.
}
