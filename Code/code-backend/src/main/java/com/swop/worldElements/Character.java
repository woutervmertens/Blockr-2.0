package com.swop.worldElements;

public class Character {

    /**
     * Current position of this character on the grid of its world.
     */
    private int[] position = {0, 0};
    /**
     * Current direction of this character in its world.
     */
    private Direction direction;

    public Character() {
        new Character(new int[]{0, 0});
    }

    public Character(int[] position) {
        this.position = position;
        this.direction = Direction.RIGHT;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
