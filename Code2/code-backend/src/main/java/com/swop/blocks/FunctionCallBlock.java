package com.swop.blocks;

import java.awt.*;

public class FunctionCallBlock extends Block implements Executable, VerticallyConnectable{
    private final FunctionDefinitionBlock definitionBlock;

    public FunctionCallBlock(Point position, int width, int height, FunctionDefinitionBlock definitionBlock) {
        super(position, width, height);
        this.definitionBlock = definitionBlock;
        executeType = ExecuteType.NonExecutable;
    }

    public FunctionDefinitionBlock getDefinitionBlock() {
        return definitionBlock;
    }

    @Override
    public void execute() {
        setBusy(true);
        getDefinitionBlock().execute();
        if (! getDefinitionBlock().isBusy()) setBusy(false);
    }

    @Override
    public Point getPlugPosition() {
        return new Point(getPosition().x /*+ step * 3*/, getPosition().y + getHeight() + step);
    }

    @Override
    public Point getSocketPosition() {
        return new Point(getPosition().x /*+ step * 3*/, getPosition().y + step);
    }
}
