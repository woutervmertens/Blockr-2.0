import java.awt.*;

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
}
