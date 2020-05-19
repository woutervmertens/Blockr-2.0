package com.swop.blocks;

import java.awt.*;

public class FunctionCallBlock extends Block implements Executable, VerticallyConnectable{
    public FunctionCallBlock(Point position, int width, int height) {
        super(position, width, height);
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
