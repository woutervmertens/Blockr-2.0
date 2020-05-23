package com.swop.command;

import com.swop.Action;
import com.swop.ProgramArea;
import com.swop.RobotAction;
import com.swop.blocks.ActionBlock;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DropBlockCommandTest {

    private Point position = new Point(1, 1);
    private int height = 2;
    private int width = 3;
    private Action action = RobotAction.MOVE_FORWARD;
    private ProgramArea programArea = new ProgramArea();
    private ActionBlock block = new ActionBlock(position, width, height, action);

    private DropBlockCommand dropBlockCommand = new DropBlockCommand(programArea, block, position.x, position.y);


    @Test
    void executeAndUndo() {
        dropBlockCommand.execute();
        assertEquals(block, dropBlockCommand.programArea.getAllBlocks().get(dropBlockCommand.programArea.getAllBlocks().size() - 1));
        dropBlockCommand.undo();
        assertFalse(dropBlockCommand.programArea.getAllBlocks().contains(block));
        dropBlockCommand.undo();
        assertFalse(dropBlockCommand.programArea.getAllBlocks().contains(block));
    }

}