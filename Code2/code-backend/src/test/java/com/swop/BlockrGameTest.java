package com.swop;

import com.swop.blocks.ActionBlock;
import com.swop.blocks.ConditionBlock;
import com.swop.blocks.WhileBlock;
import com.swop.command.DropBlockCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BlockrGameTest {

    GameWorldType gameWorldType;
    private final String[] args = {"com.swop.RobotGameWorldType"};
    private final int maxBlocks = 2;
    private BlockrGame blockrGame;
    private GameWorld gameWorld;

    private final Point aPosition = new Point(1, 1);
    private final int aWidth = 3;
    private final int aHeight = 2;
    private final Action action = RobotAction.MOVE_FORWARD;

    private final ActionBlock actionBlock = new ActionBlock(aPosition, aWidth, aHeight, action);

    private final Point wPosition = new Point(1, 3);
    private final int wWidth = 4;
    private final int wHeight = 2;

    private final WhileBlock whileBlock = new WhileBlock(wPosition, wWidth, wHeight);

    private final ConditionBlock conditionBlock = new ConditionBlock(new Point(3, 2), true, 1, 2, RobotPredicate.WALL_IN_FRONT);


    private DropBlockCommand dropBlockCommand;

    @BeforeEach
    void setup() {
        {
            try {
                Class<?> clasz = Class.forName(args[0]);
                gameWorldType = (GameWorldType) clasz.getConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        blockrGame = new BlockrGame(maxBlocks, gameWorldType);
        gameWorld = blockrGame.getGameWorld();
        whileBlock.addConditionBlock(conditionBlock);
        dropBlockCommand = new DropBlockCommand(blockrGame.getProgramArea(),actionBlock);
    }

    @Test
    void getBlockPosition() {
        assertEquals(blockrGame.getBlockPosition(whileBlock).x, wPosition.x, "The x-value of the expected and actual position of the block are different");
        assertEquals(blockrGame.getBlockPosition(whileBlock).y, wPosition.y, "The y-value of the expected and actual position of the block are different");
    }


    /**
     * checks if the functions dropBlockInPA(), getNumbBlocksInPA(), isPaletteHidden(), executeNext() and removeBlockFromPA() is implemented well
     */
    @Test
    void addRemoveNumbHidden() {
        assertTrue(blockrGame.getAllBlocksInPA().isEmpty(), "Program area isn't empty");
        assertEquals(0, blockrGame.getNumBlocksInPA(), "Number of blocks isn't 0");
        assertFalse(blockrGame.isPaletteHidden(), "maximum isn't reached");

        blockrGame.dropBlockInPA(whileBlock);
        assertEquals(1, blockrGame.getAllBlocksInPA().size(), "didn't add block to program area");
        assertEquals(1, blockrGame.getNumBlocksInPA(), "Number of blocks isn't 1");
        assertEquals(blockrGame.getProgramArea().getCurrentBlock(), whileBlock, "WhileBlock isn't the current block");
        assertFalse(blockrGame.isPaletteHidden(), "maximum isn't reached");

        blockrGame.dropBlockInPA(actionBlock);
        assertEquals(blockrGame.getProgramArea().getCurrentBlock(), whileBlock, "WhileBlock isn't the current block");
        assertEquals(2, blockrGame.getAllBlocksInPA().size(), "didn't add block to program area");
        assertEquals(2, blockrGame.getNumBlocksInPA(), "Number of blocks isn't 2");
        assertTrue(blockrGame.isPaletteHidden(), "maximum is reached");

        blockrGame.executeNext();
        assertEquals(blockrGame.getProgramArea().getCurrentBlock(), actionBlock, "ActionBlock isn't the current block");

        blockrGame.removeBlockFromPA(whileBlock, true);
        assertEquals(1, blockrGame.getAllBlocksInPA().size(), "didn't add block to program area");
        assertEquals(1, blockrGame.getNumBlocksInPA(), "Number of blocks isn't 1");
    }


}