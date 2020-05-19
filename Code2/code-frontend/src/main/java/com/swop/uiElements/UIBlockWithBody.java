package com.swop.uiElements;

import com.swop.handlers.BlockrGameFacade;

import java.awt.*;

public abstract class UIBlockWithBody extends UIBlock{
    protected int gapSize;
    protected final int pillarWidth = 10;

    public UIBlockWithBody(int width, int height, Point position, String text, BlockTypes type, Color color, Color highlightColor, BlockrGameFacade facade, int gapSize) {
        super(width, height, position, text, type, color, highlightColor, facade);
        this.gapSize = gapSize;
    }

    public int getGapSize() {
        return gapSize;
    }

    public void setGapSize(int gapSize) {
        this.gapSize = gapSize;
    }

    @Override
    public int getHeight() {
        return height + getGapSize() + pillarWidth;
    }
}
