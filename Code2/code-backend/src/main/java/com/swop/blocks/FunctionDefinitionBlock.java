package com.swop.blocks;

import java.awt.*;

// TODO: fix the executable issue
public class FunctionDefinitionBlock extends BlockWithBody implements Executable{

    public FunctionDefinitionBlock(Point position, int width, int height) {
        super(position, width, height);
        executeType = ExecuteType.NonWorldChanging;
    }

    @Override
    protected void handleEndOfBody() {
        setBusy(false);
    }
}
