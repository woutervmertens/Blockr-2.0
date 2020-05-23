package com.swop;

import com.swop.blocks.ActionBlock;
import com.swop.blocks.ConditionBlock;
import com.swop.blocks.WhileBlock;
import com.swop.command.DropBlockCommand;
import com.swop.command.ExecuteCommand;
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
        dropBlockCommand = new DropBlockCommand(blockrGame.getProgramArea(),actionBlock, wPosition.x, wPosition.y);
        actionBlock.setBlockrGame(blockrGame);
        whileBlock.setBlockrGame(blockrGame);
        conditionBlock.setBlockrGame(blockrGame);
    }

    @Test
    void getBlockPosition() {
        assertEquals(blockrGame.getBlockPosition(whileBlock).x, wPosition.x, "The x-value of the expected and actual position of the block are different");
        assertEquals(blockrGame.getBlockPosition(whileBlock).y, wPosition.y, "The y-value of the expected and actual position of the block are different");
    }

    @Test
    void getProgram() {
        assertEquals(blockrGame.getProgramArea().getProgram(),blockrGame.getProgram());
    }

    @Test
    void dropBlockInPAAt() {
        blockrGame.dropBlockInPAAt(whileBlock, wPosition.x, wPosition.y);
        assertFalse(blockrGame.getProgramArea().getAllBlocks().isEmpty());
        assertEquals(wPosition, blockrGame.getProgramArea().getAllBlocks().get(0).getPosition());
    }

    @Test
    void removeBlockFromPA() {
        blockrGame.dropBlockInPAAt(whileBlock, wPosition.x, wPosition.y);
        blockrGame.dropBlockInPAAt(actionBlock, wPosition.x, wPosition.y);
        blockrGame.removeBlockFromPA(whileBlock,false);
        assertFalse(blockrGame.getAllBlocksInPA().contains(whileBlock));
        blockrGame.removeBlockFromPA(actionBlock,true);
        assertFalse(blockrGame.getAllBlocksInPA().contains(actionBlock));
        blockrGame.undoCommand();
        assertTrue(blockrGame.getAllBlocksInPA().contains(actionBlock));
    }

    @Test
    void executeNext() {
        int[] startPos = ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition();
        blockrGame.dropBlockInPAAt(actionBlock, wPosition.x, wPosition.y);
        assertArrayEquals(startPos, ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition());
        blockrGame.executeNext();
        assertNotEquals(startPos[1], ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition()[1]);
        blockrGame.executeNext();
        assertArrayEquals(startPos, ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition());
    }

    @Test
    void getNextToBeExecutedBlock() {
        blockrGame.dropBlockInPAAt(actionBlock, wPosition.x, wPosition.y);
        assertEquals(actionBlock,blockrGame.getNextToBeExecutedBlock());
        blockrGame.removeBlockFromPA(actionBlock,false);
        whileBlock.insertBodyBlockAtIndex(actionBlock,0);
        whileBlock.insertBodyBlockAtIndex(actionBlock,1);
        whileBlock.addConditionBlock(new ConditionBlock(wPosition,false,1,1,null));
        whileBlock.addConditionBlock(conditionBlock);
        blockrGame.dropBlockInPAAt(whileBlock,wPosition.x,wPosition.y);
        blockrGame.executeNext();
        assertEquals(actionBlock,blockrGame.getNextToBeExecutedBlock());
    }

    @Test
    void resetEverything() {
        int[] startPos = ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition();
        blockrGame.dropBlockInPAAt(actionBlock, wPosition.x, wPosition.y);
        assertArrayEquals(startPos, ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition());
        blockrGame.executeNext();
        assertNotEquals(startPos[1], ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition()[1]);
        blockrGame.resetEverything();
        assertArrayEquals(startPos, ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition());
    }

    @Test
    void getNumBlocksInPA() {
        assertEquals(0, blockrGame.getNumBlocksInPA(), "Number of blocks isn't 0");
        blockrGame.dropBlockInPAAt(whileBlock, wPosition.x, wPosition.y);
        assertEquals(1, blockrGame.getNumBlocksInPA(), "Number of blocks isn't 1");
        blockrGame.dropBlockInPAAt(actionBlock, wPosition.x, wPosition.y);
        assertEquals(2, blockrGame.getNumBlocksInPA(), "Number of blocks isn't 2");
        blockrGame.removeBlockFromPA(whileBlock, true);
        assertEquals(1, blockrGame.getNumBlocksInPA(), "Number of blocks isn't 1");
    }

    @Test
    void getNumBlocksInProgram() {
        assertEquals(0, blockrGame.getNumBlocksInProgram(), "Number of blocks isn't 0");
        blockrGame.dropBlockInPAAt(actionBlock, wPosition.x, wPosition.y);
        assertEquals(1, blockrGame.getNumBlocksInProgram(), "Number of blocks isn't 1");
        blockrGame.dropBlockInPAAt(actionBlock, wPosition.x, wPosition.y);
        assertEquals(1, blockrGame.getNumBlocksInProgram(), "Number of blocks isn't 1");
    }

    @Test
    void getAllBlocksInPA() {
        assertTrue(blockrGame.getAllBlocksInPA().isEmpty(), "Program area isn't empty");
        blockrGame.dropBlockInPAAt(whileBlock, wPosition.x, wPosition.y);
        assertEquals(1, blockrGame.getAllBlocksInPA().size(), "didn't add block to program area");
        blockrGame.dropBlockInPAAt(actionBlock, wPosition.x, wPosition.y);
        assertEquals(2, blockrGame.getAllBlocksInPA().size(), "didn't add block to program area");
        blockrGame.removeBlockFromPA(whileBlock, true);
        assertEquals(1, blockrGame.getAllBlocksInPA().size(), "didn't add block to program area");
    }

    @Test
    void getBlockInPaAt() {
        blockrGame.dropBlockInPAAt(actionBlock, wPosition.x, wPosition.y);
        assertEquals(actionBlock,blockrGame.getBlockInPaAt(wPosition.x + 1,wPosition.y + 1));
    }

    @Test
    void isPaletteHidden() {
        assertFalse(blockrGame.isPaletteHidden(), "maximum isn't reached");
        blockrGame.dropBlockInPAAt(whileBlock, wPosition.x, wPosition.y);
        assertFalse(blockrGame.isPaletteHidden(), "maximum isn't reached");
        blockrGame.dropBlockInPAAt(actionBlock, wPosition.x, wPosition.y);
        assertTrue(blockrGame.isPaletteHidden(), "maximum is reached");
        blockrGame.removeBlockFromPA(whileBlock, true);
        assertFalse(blockrGame.isPaletteHidden(), "maximum isn't reached");
    }

    @Test
    void getGameWorld() {
        assertEquals(gameWorld, blockrGame.getGameWorld());
    }

    @Test
    void getGameWorldType() {
        assertEquals(gameWorldType,blockrGame.getGameWorldType());
    }

    @Test
    void undoCommand() {
        int[] startPos = ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition();
        blockrGame.dropBlockInPAAt(actionBlock, wPosition.x, wPosition.y);
        assertArrayEquals(startPos, ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition());
        blockrGame.executeNext();
        assertNotEquals(startPos[1], ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition()[1]);
        blockrGame.undoCommand();
        assertArrayEquals(startPos, ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition());
    }

    @Test
    void redoCommand() {
        int[] startPos = ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition();
        blockrGame.dropBlockInPAAt(actionBlock, wPosition.x, wPosition.y);
        assertArrayEquals(startPos, ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition());
        blockrGame.executeNext();
        assertNotEquals(startPos[1], ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition()[1]);
        blockrGame.undoCommand();
        assertArrayEquals(startPos, ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition());
        blockrGame.redoCommand();
        assertNotEquals(startPos[1], ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition()[1]);
    }

    @Test
    void executeCommand() {
        int[] startPos = ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition();
        assertArrayEquals(startPos, ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition());
        blockrGame.executeCommand(new ExecuteCommand(blockrGame,actionBlock));
        assertNotEquals(startPos[1], ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition()[1]);
    }

    @Test
    void reset(){
        int[] startPos = ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition();
        blockrGame.dropBlockInPAAt(actionBlock, wPosition.x, wPosition.y);
        assertArrayEquals(startPos, ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition());
        blockrGame.executeNext();
        assertNotEquals(startPos[1], ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition()[1]);
        blockrGame.reset();
        assertArrayEquals(startPos, ((RobotGameWorld)blockrGame.getGameWorld()).getRobot().getPosition());
    }
}