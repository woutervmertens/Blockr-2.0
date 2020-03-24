package com.swop.worldElements;

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
        grid[goalPosition[0]][goalPosition[1]] = Square.FLAG;
        this.grid = grid;
        character = new Character(new int[]{1,4});
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

    public void moveForward() {
        switch (getCharacter().getDirection()) {
            case UP:
                int[] newPosUp = {getCharacter().getPosition()[0], getCharacter().getPosition()[1] - 1};
                if (isValidPosition(newPosUp)) {
                    getCharacter().setPosition(newPosUp);
                } else throw new IllegalStateException("Cannot move forward because of wall");
                break;

            case DOWN:
                int[] newPosDown = {getCharacter().getPosition()[0], getCharacter().getPosition()[1] + 1};
                if (isValidPosition(newPosDown)) {
                    getCharacter().setPosition(newPosDown);
                } else throw new IllegalStateException("Cannot move forward because of wall");
                break;

            case LEFT:
                int[] newPosLeft = {getCharacter().getPosition()[0] - 1, getCharacter().getPosition()[1]};
                if (isValidPosition(newPosLeft)) {
                    getCharacter().setPosition(newPosLeft);
                } else throw new IllegalStateException("Cannot move forward because of wall");
                break;

            case RIGHT:
                int[] newPosRight = {getCharacter().getPosition()[0] + 1, getCharacter().getPosition()[1]};
                if (isValidPosition(newPosRight)) {
                    getCharacter().setPosition(newPosRight);
                } else throw new IllegalStateException("Cannot move forward because of wall");
                break;
        }
    }

    public void turn(Direction dir) {
        if (dir == Direction.LEFT) {
            switch (character.getDirection()) {
                case UP:
                    character.setDirection(Direction.LEFT);
                    break;
                case RIGHT:
                    character.setDirection(Direction.UP);
                    break;
                case DOWN:
                    character.setDirection(Direction.RIGHT);
                    break;
                case LEFT:
                    character.setDirection(Direction.DOWN);
                    break;
            }
        } else {
            switch (character.getDirection()) {
                case UP:
                    character.setDirection(Direction.RIGHT);
                    break;
                case RIGHT:
                    character.setDirection(Direction.DOWN);
                    break;
                case DOWN:
                    character.setDirection(Direction.LEFT);
                    break;
                case LEFT:
                    character.setDirection(Direction.UP);
                    break;
            }
        }
    }

    private boolean isPositionInBoundaries(int[] position) {
        return position[0] >= 0 && position[1] >= 0 &&
                position[1] <= getGrid().length-1 && position[0] <= getGrid()[0].length-1;
    }

    private boolean isValidPosition(int[] position) {
        try {
            return isPositionInBoundaries(position) && getGrid()[position[0]][position[1]].isPassable();
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}


