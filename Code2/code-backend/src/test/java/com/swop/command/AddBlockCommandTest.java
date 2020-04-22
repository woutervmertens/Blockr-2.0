package com.swop.command;

import com.swop.Action;
import com.swop.blocks.ActionBlock;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class AddBlockCommandTest {

    private Point position = new Point(1,1);
    private int height =2;
    private int width = 3;
    private Action action = Action.MOVE_FORWARD;
    private ActionBlock block = new ActionBlock(position, width, height, action);

    private AddBlockCommand addBlockCommand = new AddBlockCommand(block);



    @Test
    void executeAndUndo() {
        assertTrue(addBlockCommand.programArea.getAllBlocks().isEmpty());
        addBlockCommand.execute();
        assertEquals(block, addBlockCommand.programArea.getAllBlocks().get(0));
        addBlockCommand.undo();
        assertTrue(addBlockCommand.programArea.getAllBlocks().isEmpty());
    }

}