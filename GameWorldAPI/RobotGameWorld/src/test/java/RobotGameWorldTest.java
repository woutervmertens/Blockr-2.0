import org.junit.jupiter.api.BeforeEach;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RobotGameWorldTest {
    private RobotGameWorld gameWorld;
    private ArrayList<int[]> walls = new ArrayList<int[]>();

    @BeforeEach
    void setUp(){
        gameWorld = new RobotGameWorld();
        walls.add(new int[]{3,4});
    }

    @org.junit.jupiter.api.Test
    void setSize() {
        gameWorld.setSize(25);
        assertEquals(gameWorld.getSize(), 25);
    }

    @org.junit.jupiter.api.Test
    void setDrawPosition() {
        gameWorld.setDrawPosition(new Point(200,0));
        assertEquals(gameWorld.getDrawPosition(), new Point(200,0));
    }

    @org.junit.jupiter.api.Test
    void setRobot() {
        gameWorld.setRobot(Direction.LEFT,new int[]{2,1});
        assertEquals(gameWorld.getRobot().getDirection(),Direction.LEFT,"Robot direction failed");
        assertEquals(gameWorld.getRobot().getPosition()[0],2,"Robot y position failed");
        assertEquals(gameWorld.getRobot().getPosition()[1],1,"Robot x position failed");
    }

    @org.junit.jupiter.api.Test
    void setGrid() {
        Square[][] grid = new Square[5][5];
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(grid[i],Square.AIR);
        }
        grid[3][4] = Square.WALL;
        grid[2][0] = Square.GOAL;
        gameWorld.setGrid(walls,new int[]{2,0});
        assertTrue(compareGrid(gameWorld.getGrid(),grid));
    }

    boolean compareGrid(Square[][] grid1, Square[][] grid2){
        if(grid1.length != grid2.length || grid1[0].length != grid2[0].length) return false;
        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[0].length; j++) {
                if(grid1[i][j] != grid2[i][j]) return false;
            }
        }
        return true;
    }

    @org.junit.jupiter.api.Test
    void doAction() {
    }

    @org.junit.jupiter.api.Test
    void evaluate() {
    }

    @org.junit.jupiter.api.Test
    void createSnapshot() {
    }

    @org.junit.jupiter.api.Test
    void restoreSnapshot() {
    }
}