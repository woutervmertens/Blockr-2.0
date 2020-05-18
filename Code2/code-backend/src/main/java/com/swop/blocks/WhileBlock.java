package com.swop.blocks;

import java.awt.*;

public class WhileBlock extends StatementBlock {

    public WhileBlock(Point position, int width, int height) {
        super(position, width, height);
    }

    protected void handleEndOfBody() {
        if (isConditionValid()) {
            setCurrent(bodyBlocks.get(0));
        } else {
            setDone(true);
            setBusy(false);
        }
    }
}
