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
        assertTrue(compareIntArr(gameWorld.getRobot().getPosition(),new int[]{2,1}),"Robot position failed");
    }

    @org.junit.jupiter.api.Test
    void setGrid() {
        Square[][] grid = new Square[5][5];
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(grid[i],Square.AIR);
        }
        grid[3][4] = Square.WALL;
        grid[2][0] = Square.GOAL;
        gameWorld.setGrid(5,walls,new int[]{2,0});
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

    boolean compareIntArr(int[] i1, int[] i2){
        if(i1.length != i2.length) return false;
        for (int i = 0; i < i1.length; i++) {
            if(i1[i] != i2[i]) return false;
        }
        return true;
    }

    @org.junit.jupiter.api.Test
    void doAction() {
        gameWorld.setRobot(Direction.LEFT,new int[]{2,1});
        gameWorld.setGrid(5,walls,new int[]{2,0});
        //Test legal action
        assertEquals(gameWorld.doAction(Action.TURN_RIGHT),SuccessState.SUCCESS);
        //Test turn right
        assertEquals(gameWorld.getRobot().getDirection(),Direction.UP);
        gameWorld.doAction(Action.TURN_LEFT);
        //Test turn left
        assertEquals(gameWorld.getRobot().getDirection(),Direction.LEFT);
        gameWorld.doAction(Action.MOVE_FORWARD);
        //Test moved
        assertTrue(compareIntArr(gameWorld.getRobot().getPosition(),new int[]{2,0}));
        gameWorld.setRobot(Direction.RIGHT,new int[]{3,3});
        //Test illegal action
        assertEquals(gameWorld.doAction(Action.MOVE_FORWARD),SuccessState.FAILURE);
        gameWorld.setRobot(Direction.LEFT,new int[]{2,1});
        //Test goal reached
        assertEquals(gameWorld.doAction(Action.MOVE_FORWARD),SuccessState.GOAL_REACHED);
    }

    @org.junit.jupiter.api.Test
    void evaluate() {
        gameWorld.setGrid(5,walls,new int[]{2,0});
        gameWorld.setRobot(Direction.RIGHT,new int[]{3,3});
        assertTrue(gameWorld.evaluate(Predicate.WALL_IN_FRONT));
        gameWorld.setRobot(Direction.LEFT,new int[]{3,3});
        assertFalse(gameWorld.evaluate(Predicate.WALL_IN_FRONT));
    }

    @org.junit.jupiter.api.Test
    void createSnapshot() {
        RobotSnapshot snap;
        Square[][] grid = new Square[5][5];
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(grid[i],Square.AIR);
        }
        grid[3][4] = Square.WALL;
        grid[2][0] = Square.GOAL;
        gameWorld.setGrid(5,walls,new int[]{2,0});
        gameWorld.setRobot(Direction.LEFT,new int[]{2,1});
        snap = (RobotSnapshot) gameWorld.createSnapshot();

        assertTrue(compareGrid(snap.getGrid(),grid));
        assertEquals(snap.getRobot().getDirection(),Direction.LEFT,"Snapshot Robot direction failed");
        assertTrue(compareIntArr(snap.getRobot().getPosition(),new int[]{2,1}),"Snapshot Robot position failed");
    }

    @org.junit.jupiter.api.Test
    void restoreSnapshot() {
        Square[][] grid = new Square[5][5];
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(grid[i],Square.AIR);
        }
        grid[3][4] = Square.WALL;
        grid[2][0] = Square.GOAL;
        Robot robot = new Robot();
        robot.setDirection(Direction.LEFT);
        robot.setPosition(new int[]{2,1});
        RobotSnapshot snap = new RobotSnapshot();
        snap.setGrid(grid);
        snap.setRobot(robot);
        gameWorld.restoreSnapshot(snap);
        assertTrue(compareGrid(gameWorld.getGrid(),grid));
        assertTrue(compareIntArr(gameWorld.getRobot().getPosition(),robot.getPosition()));
        assertEquals(gameWorld.getRobot().getDirection(),robot.getDirection());
    }
}