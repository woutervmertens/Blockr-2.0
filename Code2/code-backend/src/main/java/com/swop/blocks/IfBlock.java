package com.swop.blocks;

import java.awt.*;

public class IfBlock extends StatementBlock {
    public IfBlock(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    protected void handleEndOfBody() {
        setBusy(false);
        setNextBodyBlock(null);
    }


}
