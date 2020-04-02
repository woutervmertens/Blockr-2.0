package com.swop.worldElements;

import java.awt.*;
import java.util.Arrays;

public class GameWorld {
    private Character character;
    private final Square[][] grid;

    public GameWorld(int[] gridDimension, int[] goalPosition) {
        Square[][] grid = new Square[gridDimension[0]][gridDimension[1]];
        for (Square[] squares : grid) {
            Arrays.fill(squares, Square.WALL);
        }
        // TODO: make the air positions and goal positions via parameters.
        grid[1][4] = Square.AIR;
        grid[2][4] = Square.AIR;
        grid[2][3] = Square.AIR;
        grid[3][3] = Square.AIR;
        grid[goalPosition[1]][goalPosition[0]] = Square.FLAG;
        this.grid = grid;
        character = new Character(new Point(1, 4));
    }

    public Character getCharacter() {
        return character;
    }

    public Square[][] getGrid() {
        return grid;
    }

    public void reset() {
        getCharacter().setPosition((new Character()).getPosition());
        getCharacter().setDirection((new Character()).getDirection());
    }

    public Point getPositionInFrontOfCharacter() {
        Point newPos;
        switch (getCharacter().getDirection()) {
            case UP:
                newPos = new Point(getCharacter().getPosition().x, getCharacter().getPosition().y - 1);
                break;
            case DOWN:
                newPos = new Point(getCharacter().getPosition().x, getCharacter().getPosition().y + 1);
                break;
            case LEFT:
                newPos = new Point(getCharacter().getPosition().x - 1, getCharacter().getPosition().y);
                break;
            case RIGHT:
                newPos = new Point(getCharacter().getPosition().x + 1, getCharacter().getPosition().y);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + getCharacter().getDirection());
        }
        return newPos;
    }

    public boolean isPassableInFrontOfCharacter() {
        return isPassable(getPositionInFrontOfCharacter());
    }

    private boolean isPassable(Point pos) {
        return getGrid()[pos.x][pos.y].isPassable();
    }

    public void moveForward() {
        Point newPos = getPositionInFrontOfCharacter();
        if (isValidPosition(newPos)) {
            getCharacter().setPosition(newPos);
        } else throw new IllegalStateException("Cannot move forward because of wall");

    }

    public void turn(boolean clockwise) {
        // Just setting the next direction in the enumeration
        if (clockwise) getCharacter().setDirection(Direction.values()[(getCharacter().getDirection().ordinal() + 1) % Direction.values().length]);
        else getCharacter().setDirection(Direction.values()[(getCharacter().getDirection().ordinal() - 1) % Direction.values().length]);
    }

    private boolean isPositionInBoundaries(Point position) {
        return position.x >= 0 && position.y >= 0 &&
                position.y <= getGrid().length - 1 && position.x <= getGrid()[0].length - 1;
    }

    /**
     * Check whether the given position is a valid position for the character of this world.
     */
    private boolean isValidPosition(Point position) {
        try {
            return isPositionInBoundaries(position) && getGrid()[position.x][position.y].isPassable();
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}


