package com.swop.blocks;

import java.awt.*;

public class FunctionCallBlock extends Block implements Executable, VerticallyConnectable{
    private FunctionDefinitionBlock definitionBlock;

    public FunctionCallBlock(Point position, int width, int height, FunctionDefinitionBlock definitionBlock) {
        super(position, width, height);
        this.definitionBlock = definitionBlock;
    }

    @Override
    public void execute() {

    }

    @Override
    public Point getPlugPosition() {
        return null;
    }

    @Override
    public Point getSocketPosition() {
        return null;
    }
}
