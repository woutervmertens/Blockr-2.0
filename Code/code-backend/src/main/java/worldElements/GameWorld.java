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

//    TODO: Refactor this method to Program class:
//    /**
//     * Goes through the given list of ConditionBlocks from back to front and calls their checks
//     *
//     * @param conditions A list of ConditionBlocks to check.
//     * @return The overall result of all conditions.
//     */
//    public boolean checkConditions(ArrayList<ConditionBlock> conditions) {
//        boolean result = true;
//        Collections.reverse(conditions);
//        Square sq;
//        //Get square in front
//        switch (character.getDirection()) {
//            case UP:
//                sq = (grid[character.getPosition().y - 1][character.getPosition().x]);
//                break;
//            case RIGHT:
//                sq = (grid[character.getPosition().y][character.getPosition().x + 1]);
//                break;
//            case DOWN:
//                sq = (grid[character.getPosition().y + 1][character.getPosition().x]);
//                break;
//            case LEFT:
//                sq = (grid[character.getPosition().y][character.getPosition().x - 1]);
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + character.getDirection());
//        }
//        for (ConditionBlock cond : conditions) {
//            result = cond.checkCondition(result, sq);
//        }
//
//        return result;
//    }
}
