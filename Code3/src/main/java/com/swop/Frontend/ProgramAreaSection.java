package com.swop;

import com.swop.uiElements.UIBlock;

import java.awt.*;
import java.util.Collection;

public class ProgramAreaSection extends WindowSection{
    public ProgramAreaSection(Point pos, int width, int height) {
        super(pos, width, height);
    }

    void draw(Graphics g, Collection<UIBlock> uiBlocks) {
        g.setColor(Color.PINK);
        g.fillRect(position.x, position.y, width, height);

        for (UIBlock block : uiBlocks) {
            drawBlock(block, g);
        }
    }
}
