package com.swop.worldElements;

public class Square {
    private final boolean isPassable;

    public Square(boolean isPassable) {
        this.isPassable = isPassable;
    }

    public boolean isPassable() {
        return isPassable;
    }
}
