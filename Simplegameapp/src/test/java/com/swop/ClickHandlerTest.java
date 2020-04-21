package com.swop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClickHandlerTest {
    private GameWorld gameWorld;
    private ClickHandler handler;

    @BeforeEach
    void setUp() {
        gameWorld = (new RobotGameWorldType()).createNewInstance();
        handler = new ClickHandler(gameWorld);
    }

    @Test
    void handleClick() {
        Snapshot snap = gameWorld.createSnapshot();
        handler.HandleClick(Action.MOVE_FORWARD);
        assertFalse(compareSnapshot((RobotSnapshot) snap, (RobotSnapshot) gameWorld.createSnapshot()));
        handler.HandleClick(Action.MOVE_FORWARD);
        snap = gameWorld.createSnapshot();
        handler.HandleClick(Action.MOVE_FORWARD);
        assertTrue(compareSnapshot((RobotSnapshot) snap, (RobotSnapshot) gameWorld.createSnapshot()));
    }

    private boolean compareSnapshot(RobotSnapshot snap, RobotSnapshot snap2){
        return (snap.getRobot().getDirection() == snap2.getRobot().getDirection())
                && (compareIntArr(snap.getRobot().getPosition(),snap2.getRobot().getPosition()))
                && (compareGrid(snap.getGrid(),snap2.getGrid()));
    }

    private boolean compareGrid(Square[][] grid1, Square[][] grid2){
        if(grid1.length != grid2.length || grid1[0].length != grid2[0].length) return false;
        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[0].length; j++) {
                if(grid1[i][j] != grid2[i][j]) return false;
            }
        }
        return true;
    }

    private boolean compareIntArr(int[] i1, int[] i2){
        if(i1.length != i2.length) return false;
        for (int i = 0; i < i1.length; i++) {
            if(i1[i] != i2[i]) return false;
        }
        return true;
    }
}