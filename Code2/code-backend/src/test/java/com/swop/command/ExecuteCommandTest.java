package com.swop.command;

import com.swop.Action;
import com.swop.blocks.ActionBlock;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ExecuteCommandTest {

    private Point position = new Point(1,1);
    private int height =2;
    private int width = 3;
    private Action action = Action.MOVE_FORWARD;
    private ActionBlock block = new ActionBlock(position, width, height, action);

    private ExecuteCommand executeCommand = new ExecuteCommand(block);

    @Test
    void execute() {
        // TODO: 15/04/2020 test execute
    }

    @Test
    void undo() {
    }
}