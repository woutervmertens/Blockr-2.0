package com.swop.uiElements;

import java.awt.*;

public abstract class UIBlockWithBody extends UIBlock{
    protected int gapSize;
    protected final int pillarWidth = 10;

    public UIBlockWithBody(BlockTypes.stdBlockData data, int gapSize){
        super(data);
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
