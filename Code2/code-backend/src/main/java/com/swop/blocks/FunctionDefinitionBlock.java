package com.swop.blocks;

import java.awt.*;

public class FunctionDefinitionBlock extends BlockWithBody{
    protected FunctionDefinitionBlock(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    protected void handleEndOfBody() {

    }

    @Override
    public Point getSocketPosition() {
        return null;
    }

    @Override
    public Point getPlugPosition() {
        return null;
    }
}
