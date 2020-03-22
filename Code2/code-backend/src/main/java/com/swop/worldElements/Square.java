package com.swop.worldElements;

public enum Square {
    WALL(false),
    AIR(true),
    FLAG(true);

    private final boolean isPassable;

    Square(boolean isPassable) {
        this.isPassable = isPassable;
    }

    public boolean isPassable() {
        return isPassable;
    }
}
