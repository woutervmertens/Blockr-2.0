package com.swop.blocks;


import java.awt.*;

public abstract class Block {
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    private Point position;

    private boolean isHighlight;

    public boolean isHighlight() {
        return isHighlight;
    }

    public void setHighlight(boolean highlight) {
        isHighlight = highlight;
    }
}
