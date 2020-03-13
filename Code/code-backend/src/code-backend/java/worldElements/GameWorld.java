package worldElements;

import blocks.ConditionBlock;
import worldElements.Character;
import worldElements.Square;

import java.util.ArrayList;
import java.util.Collections;

public class GameWorld {

    public GameWorld(int[] gridDimension, int[] goalPosition) {
        // TODO: build whole square with nested for loop
        // TODO: include wall positions in constructor params
        grid = new Square[gridDimension[0]][gridDimension[1]];
        this.goalPosition = goalPosition;
        this.character = new Character();
    }

    /**
     * Grid representing the game world.
     * Every cell on the grid is a Square. Pos {0,0} on the grid is the lowest leftmost position.
     */
    private Square[][] grid;

    public Square[][] getGrid() {
        return grid;
    }

    public void setGrid(Square[][] grid) {
        this.grid = grid;
    }

    /**
     * Checks whether any character can have the given position in this world.
     */
    public boolean characterCanHaveAsPosition(int[] position) {
        if (isPositionInBoundaries(position)) return false;
        // non-passable square
        return grid[position[1]][position[0]].isPassable();
    }

    public boolean isPositionInBoundaries(int[] position) {
        return position[0] < 0 || position[1] < 0 ||
                position[1] >= getGrid().length || position[0] >= getGrid()[0].length;
    }

    /**
     * The goal position of this world that has to be reached by the character controlled by the player.
     */
    private int[] goalPosition;

    public int[] getGoalPosition() {
        return goalPosition;
    }

    public void setGoalPosition(int[] goalPosition) {
        this.goalPosition = goalPosition;
    }

    /**
     * The character of this game world controlled by the player of the game.
     */
    private Character character;

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    private boolean isValidMove(int[] position) {
        if (isPositionInBoundaries(position) && getGrid()[position[0]][position[1]].isPassable()) {
            return true;
        } else {
            return false;
        }
    }

    public void move() {
        switch (getCharacter().getDirection()) {
            case UP:
                getCharacter().setPosition(new int[]{getCharacter().getPosition()[0], getCharacter().getPosition()[1] + 1});
                break;

            case DOWN:
                getCharacter().setPosition(new int[]{getCharacter().getPosition()[0], getCharacter().getPosition()[1] - 1});
                break;

            case LEFT:
                getCharacter().setPosition(new int[]{getCharacter().getPosition()[0] - 1, getCharacter().getPosition()[1]});
                break;

            case RIGHT:
                getCharacter().setPosition(new int[]{getCharacter().getPosition()[0] + 1, getCharacter().getPosition()[1]});
                break;
        }
    }

    public void turn() {
        if (getCharacter().getDirection() == Direction.LEFT) {
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
}


