package com.swop.command;

import com.swop.*;
import com.swop.blocks.ActionBlock;
import com.swop.blocks.ConditionBlock;
import com.swop.blocks.IfBlock;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ExecuteCommandTest {

    private String[] args= {"com.swop.RobotGameWorldType"};
    private Point position = new Point(1,1);
    private int height =2;
    private int width = 3;
    private Action action = Action.MOVE_FORWARD;
    private ActionBlock block = new ActionBlock(position, width, height, action);


    GameWorldType gameWorldType;

    {
        try {
            Class<?> clasz = Class.forName(args[0]);
            gameWorldType = (GameWorldType) clasz.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace(); }
    }

    private int maxBlocks = 5;
    BlockrGame blockrGame = BlockrGame.createInstance(maxBlocks, gameWorldType);

    private GameWorld gameWorld = blockrGame.getGameWorld();

    private RobotSnapshot snapshot = (RobotSnapshot) gameWorld.createSnapshot();
    private ExecuteCommand executeCommand = new ExecuteCommand(block);

    private ConditionBlock conditionBlock = new ConditionBlock(new Point(5,6), true, 2, 2);
    private ExecuteCommand executeCommand1 = new ExecuteCommand(conditionBlock);

    @Test
    void executeAndUndo() {
        executeCommand.execute();
        RobotSnapshot snapshot1 = (RobotSnapshot) gameWorld.createSnapshot();
        assertNotEquals(snapshot.getRobot().getPosition(), snapshot1.getRobot().getPosition(), "position of the robot is the same in both snapshots");
        executeCommand.undo();
        RobotSnapshot snapshot2 = (RobotSnapshot) gameWorld.createSnapshot();
        assertEquals(snapshot.getRobot().getPosition(), snapshot2.getRobot().getPosition(), "position of the robot isn't the same in both snapshots");

    }

    @Test
    void executeNonExecutable(){
        executeCommand1.execute();
        RobotSnapshot snapshot1 = (RobotSnapshot) gameWorld.createSnapshot();
        assertEquals(snapshot.getRobot().getPosition(), snapshot1.getRobot().getPosition(), "position of the robot isn't the same in both snapshots");
    }

}