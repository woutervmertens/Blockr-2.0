package com.swop.blocks;

import java.awt.*;

/**
 * A block that calls a function.
 */
public class FunctionCallBlock extends Block implements Executable, VerticallyConnectable{
    private final FunctionDefinitionBlock definitionBlock;

    /**
     * Creates a functionCallBlock with the given position, width, height and definitionBlock.
     * @param position the given position of the functionCallBlock.
     * @param width the given width of the functionCallBlock.
     * @param height the given height of the functionCallBlock.
     * @param definitionBlock the given functionDefinitionBlock that it calls.
     */
    public FunctionCallBlock(Point position, int width, int height, FunctionDefinitionBlock definitionBlock) {
        super(position, width, height);
        this.definitionBlock = definitionBlock;
        executeType = ExecuteType.NonWorldChanging;
    }

    public FunctionDefinitionBlock getDefinitionBlock() {
        return definitionBlock;
    }

    /**
     * Executes the nextBodyBlock of the definitionBlock if it exists.
     */
    @Override
    public void execute() {
        setBusy(true);
        getDefinitionBlock().execute();
        if (! getDefinitionBlock().isBusy()) setBusy(false);
    }

    /**
     * @return Returns the plug position of the functionCallBlock.
     */
    @Override
    public Point getPlugPosition() {
        return new Point(getPosition().x /*+ step * 3*/, getPosition().y + getHeight() + step);
    }

    /**
     * @return Returns the plug position of the functionCallBlock.
     */
    @Override
    public Point getSocketPosition() {
        return new Point(getPosition().x /*+ step * 3*/, getPosition().y + step);
    }
}
