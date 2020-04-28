package com.swop.command;

import com.swop.Action;
import com.swop.RobotAction;
import com.swop.blocks.ActionBlock;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DeleteBlockCommandTest {

    private Point position = new Point(1, 1);
    private int height = 2;
    private int width = 3;
    private Action action = (Action) RobotAction.MOVE_FORWARD;
    private ActionBlock block = new ActionBlock(position, width, height, action);

    private DeleteBlockCommand deleteBlockCommand = new DeleteBlockCommand(block);


    @Test
    void executeAndUndo() {
        deleteBlockCommand.programArea.dropBlock(block);
        assertEquals(block, deleteBlockCommand.programArea.getAllBlocks().get(deleteBlockCommand.programArea.getAllBlocks().size() - 1));
        deleteBlockCommand.execute();
        assertFalse(deleteBlockCommand.programArea.getAllBlocks().contains(block));
        deleteBlockCommand.undo();
        assertEquals(block, deleteBlockCommand.programArea.getAllBlocks().get(deleteBlockCommand.programArea.getAllBlocks().size() - 1));
    }

}