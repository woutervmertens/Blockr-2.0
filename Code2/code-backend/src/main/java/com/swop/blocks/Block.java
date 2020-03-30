package com.swop.blocks;


import com.swop.worldElements.GameWorld;

import java.awt.*;

public abstract class Block {
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    private Point position;

    public abstract void execute(GameWorld world);

}
