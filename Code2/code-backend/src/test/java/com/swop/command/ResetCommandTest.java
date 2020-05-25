package com.swop.command;

import com.swop.*;
import com.swop.blocks.ActionBlock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ResetCommandTest {
    GameWorldType gameWorldType;
    private Point position;
    private int height;
    private int width;
    private Action action;
    private ActionBlock block;
    BlockrGame blockrGame;
    private ExecuteCommand executeCommand;
    private ResetCommand resetCommand;

    @BeforeEach
    void setUp() {
        gameWorldType = new RobotGameWorldType();
        position = new Point(1, 1);
        height = 2;
        width = 3;
        action = RobotAction.TURN_LEFT;
        block = new ActionBlock(position, width, height, action);
        blockrGame = new BlockrGame(10, gameWorldType);
        executeCommand = new ExecuteCommand(blockrGame, block);
        resetCommand = new ResetCommand(blockrGame);
    }

    @Test
    void execute() {
        Snapshot snap = blockrGame.getGameWorld().createSnapshot();
        block.setBlockrGame(blockrGame);
        blockrGame.getProgramArea().dropBlock(block);
        executeCommand.execute();
        assertEquals(null,blockrGame.getProgramArea().getNextProgramBlock());
        resetCommand.execute();
        assertTrue(compareSnapshot((RobotSnapshot) snap,(RobotSnapshot)blockrGame.getGameWorld().createSnapshot()));
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

    @Test
    void undo() {
        block.setBlockrGame(blockrGame);
        blockrGame.getProgramArea().dropBlock(block);
        executeCommand.execute();
        Snapshot snap = blockrGame.getGameWorld().createSnapshot();
        assertEquals(block,blockrGame.getProgramArea().getNextProgramBlock());
        resetCommand.execute();
        resetCommand.undo();
        assertTrue(compareSnapshot((RobotSnapshot) snap,(RobotSnapshot)blockrGame.getGameWorld().createSnapshot()));
    }
}