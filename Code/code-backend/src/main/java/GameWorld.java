import Blocks.ActionBlock;
import Blocks.ConditionBlock;
import WorldElements.Character;
import WorldElements.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameWorld {
    Character character, start;
    Point goalPosition;
    Square[][] grid;

    public GameWorld(Character character, Square[][] grid, Point goalPosition) {
        this.character = character;
        this.start = character;
        this.grid = grid;
        this.goalPosition = goalPosition;
    }

    public Point getGoalPosition() {
        return goalPosition;
    }

    public void setGoalPosition(Point goalPosition) {
        this.goalPosition = goalPosition;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Square[][] getGrid() {
        return grid;
    }

    public void setGrid(Square[][] grid) {
        this.grid = grid;
    }

    /**
     * Resets player to start position and orientation
     */
    public void Reset(){
        character = start;
    }

    /**
     * Applies actionblock to the world and checks if the action was valid, if not the action gets undone
     * @param block The ActionBlock to apply
     */
    public void parse(ActionBlock block)
    {
        Character characterCopy = new Character(character);
        block.doAction(character);
        if(!checkValidPosition(character.getPosition()))
        {
            //TODO error, buiten speelveld/op muur: INVALID ACTION
            character.setPosition(characterCopy.getPosition());
        }
    }

    /**
     * @param position A position to validate.
     * @return is the given position valid
     */
    private boolean checkValidPosition(Point position) {
        //if outside of grid
        if(position.x < 0 || position.y < 0 || position.y >= grid.length || position.x >= grid[0].length){
            return false;
        }
        //if on non-passable square
        if(!grid[position.y][position.x].isPassable())
            return false;

        return true;
    }

    /**
     * Goes through the given list of ConditionBlocks from back to front and calls their checks
     * @param conditions A list of ConditionBlocks to check.
     * @return The overall result of all conditions.
     */
    public boolean checkConditions(ArrayList<ConditionBlock> conditions)
    {
        boolean result = true;
        Collections.reverse(conditions);
        Square sq;
        //Get square in front
        switch (character.getDirection()) {
            case UP:
                sq = (grid[character.getPosition().y - 1][character.getPosition().x]);
                break;
            case RIGHT:
                sq = (grid[character.getPosition().y][character.getPosition().x + 1]);
                break;
            case DOWN:
                sq = (grid[character.getPosition().y + 1][character.getPosition().x]);
                break;
            case LEFT:
                sq = (grid[character.getPosition().y][character.getPosition().x - 1]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + character.getDirection());
        }
        for (ConditionBlock cond : conditions)
        {
            result = cond.checkCondition(result,sq);
        }

        return result;
    }
}
