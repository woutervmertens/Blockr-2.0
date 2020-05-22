package com.swop.blocks;

import com.swop.*;
import com.swop.Robot;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ActionBlockTest {

    private final Point position = new Point(5, 1);
    private final int width = 3;
    private final int height = 4;
    private final Action action = RobotAction.MOVE_FORWARD;
    private BlockrGame blockrGame = new BlockrGame(10, new RobotGameWorldType());

    private final ActionBlock actionBlock = new ActionBlock(position, width, height, action);

    @Test
    void getPlugPosition() {
        Point expectedPosition = new Point(position.x, position.y + actionBlock.getHeight() + actionBlock.getStep());
        assertEquals(expectedPosition.x, actionBlock.getPlugPosition().x, "Expected x-position and actual x-position of plug are different");
        assertEquals(expectedPosition.y, actionBlock.getPlugPosition().y, "Expected y-position and actual y-position of plug are different");
    }

    @Test
    void getSocketPosition() {
        Point expectedPosition = new Point(position.x, position.y + actionBlock.getStep());
        assertEquals(expectedPosition.x, actionBlock.getSocketPosition().x, "Expected x-position and actual x-position of socket are different");
        assertEquals(expectedPosition.y, actionBlock.getSocketPosition().y, "Expected y-position and actual y-position of socket are different");
    }


    @Test
    void execute() {
        Snapshot snap = blockrGame.getGameWorld().createSnapshot();
        ActionBlock turnBlock = new ActionBlock(position,width,height,RobotAction.TURN_LEFT);
        turnBlock.setBlockrGame(blockrGame);
        turnBlock.execute();
        Snapshot snap2 = blockrGame.getGameWorld().createSnapshot();
        assertFalse(CompareSnapshots((RobotSnapshot)snap,(RobotSnapshot)snap2));
        actionBlock.setBlockrGame(blockrGame);
        actionBlock.execute();
        assertTrue(CompareSnapshots((RobotSnapshot) snap2,(RobotSnapshot) blockrGame.getGameWorld().createSnapshot()));
    }

    private boolean CompareSnapshots(RobotSnapshot a, RobotSnapshot b){
        return CompareGrid(a.getGrid(),b.getGrid()) && CompareRobot(a.getRobot(),b.getRobot());
    }

    private boolean CompareGrid(Square[][] a, Square[][] b){
        if(a.length != b.length || a[0].length != b[0].length) return false;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if(a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }

    private boolean CompareRobot(Robot a, Robot b){
        return (a.getDirection() == b.getDirection())
                && (compareIntArr(a.getPosition(),b.getPosition()));
    }

    private boolean compareIntArr(int[] i1, int[] i2){
        if(i1.length != i2.length) return false;
        for (int i = 0; i < i1.length; i++) {
            if(i1[i] != i2[i]) return false;
        }
        return true;
    }
}