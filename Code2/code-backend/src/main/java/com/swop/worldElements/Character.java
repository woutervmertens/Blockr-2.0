package com.swop.worldElements;

import java.awt.*;

public class Character {

    /**
     * Current position of this character on the grid of its world.
     */
    private Point position = new Point(0, 0);
    /**
     * Current direction of this character in its world.
     */
    private Direction direction;

    public Character() {
        new Character(new Point(0, 0));
    }

    public Character(Point position) {
        this.position = position;
        this.direction = Direction.RIGHT;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
