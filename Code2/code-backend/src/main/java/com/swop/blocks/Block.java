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
}
