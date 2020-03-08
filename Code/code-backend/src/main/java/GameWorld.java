import Blocks.ActionBlock;
import Blocks.ConditionBlock;
import WorldElements.Player;
import WorldElements.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameWorld {
    Player player;
    Point goalPosition;
    Square[][] grid;

    public GameWorld(Player player, Square[][] grid, Point goalPosition) {
        this.player = player;
        this.grid = grid;
        this.goalPosition = goalPosition;
    }

    public Point getGoalPosition() {
        return goalPosition;
    }

    public void setGoalPosition(Point goalPosition) {
        this.goalPosition = goalPosition;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Square[][] getGrid() {
        return grid;
    }

    public void setGrid(Square[][] grid) {
        this.grid = grid;
    }

    public void parse(ActionBlock block)
    {
        Player playerCopy = new Player(player);
        block.doAction(player);
        if(!checkValidPosition(player.getPosition()))
        {
            //TODO error, buiten speelveld/op muur: INVALID ACTION
            player.setPosition(playerCopy.getPosition());
        }
    }

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

    public boolean checkConditions(ArrayList<ConditionBlock> conditions)
    {
        boolean result = true;
        Collections.reverse(conditions);
        Square sq;
        //Get square in front
        switch (player.getDirection()) {
            case UP:
                sq = (grid[player.getPosition().y - 1][player.getPosition().x]);
                break;
            case RIGHT:
                sq = (grid[player.getPosition().y][player.getPosition().x + 1]);
                break;
            case DOWN:
                sq = (grid[player.getPosition().y + 1][player.getPosition().x]);
                break;
            case LEFT:
                sq = (grid[player.getPosition().y][player.getPosition().x - 1]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + player.getDirection());
        }
        for (ConditionBlock cond : conditions)
        {
            result = cond.checkCondition(result,sq);
        }

        return result;
    }
}
