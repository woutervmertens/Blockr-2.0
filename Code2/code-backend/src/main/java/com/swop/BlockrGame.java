package com.swop;

import com.swop.blocks.Block;
import com.swop.blocks.BlockWithBody;
import com.swop.command.*;

import java.awt.*;
import java.util.List;
import java.util.Stack;

public class BlockrGame {
    private final ProgramArea programArea;
    private final GameWorldType gameWorldType;
    /**
     * Maximum number of blocks that can be used
     */
    private final int maxBlocks;
    private GameWorld gameWorld;
    /**
     * Stack for all the undo's
     */
    private final Stack<ICommand> undoStack = new Stack<>();
    /**
     * Stack for all the redo's
     */
    private final Stack<ICommand> redoStack = new Stack<>();

    /**
     * Initializes BlockrGame
     *
     * @param maxBlocks     maximum number of blocks that can be used.
     * @param gameWorldType given type of gameWorld
     */
    public BlockrGame(int maxBlocks, GameWorldType gameWorldType) {
        this.maxBlocks = maxBlocks;
        this.programArea = new ProgramArea();
        this.gameWorldType = gameWorldType;
        this.gameWorld = gameWorldType.createNewInstance();
    }

    /**
     * @return Returns program that is currently in program area
     */
    public List<Block> getProgram() {
        return programArea.getProgram();
    }

    public ProgramArea getProgramArea() {
        return programArea;
    }

    /**
     * @param block Given block
     * @return Returns the position of the given block
     */
    public Point getBlockPosition(Block block) {
        return block.getPosition();
    }

    /**
     * Adds block to Program Area
     *
     * @param block given block
     */
    public void dropBlockInPAAt(Block block, int x, int y) {
        if (block == null) throw new IllegalArgumentException();
        executeCommand(new DropBlockCommand(programArea, block, x, y));
    }

    /**
     * Removes block from Program Area
     *
     * @param draggedBlock given block
     * @param isRelease    Boolean recording whether this is a release (outside PA) or not (a click)
     */
    public void removeBlockFromPA(Block draggedBlock, boolean isRelease) {
        if (isRelease) {
            executeCommand(new DeleteBlockCommand(programArea, draggedBlock));
        } else {
            programArea.removeBlockFromPA(draggedBlock);
        }
    }

    /**
     * Executes next block
     */
    public void executeNext() {
        if (programArea.getNextProgramBlock() != null) {
            executeCommand(new ExecuteCommand(this, programArea.getNextProgramBlock()));
        } else {
            executeCommand(new ResetCommand(this));
        }
    }

    public Block getNextToBeExecutedBlock() {
        if (programArea.getNextProgramBlock() instanceof BlockWithBody) {
            BlockWithBody blockWithBody = (BlockWithBody) programArea.getNextProgramBlock();

            // TODO:
//            if (blockWithBody.getNextBodyBlock() != null) {
//                return blockWithBody.getNextBodyBlock();
//            }
        }
        // TODO: else if statement call block .. OR refactor
        return programArea.getNextProgramBlock();
    }

    /**
     * Resets the game world and the program area
     */
    public void resetEverything() {
        programArea.resetProgramExecution();
        gameWorld = gameWorldType.createNewInstance();
    }

    /**
     * @return Returns the number of blocks that are in the Program Area
     */
    public int getNumBlocksInPA() {
        return programArea.getAllBlocks().size();
    }

    public int getNumBlocksInProgram() {
        int count = 0;
        for (Block block : getProgram()) {
            count += block.getCount();
        }
        return count;
    }

    /**
     * @return Returns all the blocks that are in the Program Area
     */
    public List<Block> getAllBlocksInPA() {
        return programArea.getAllBlocks();
    }

    /**
     * position of a block is (x,y)
     *
     * @param x x-value of position
     * @param y y-value of position
     * @return returns a block if there is one at the given position otherwise null will be returned.
     */
    public Block getBlockInPaAt(int x, int y) {
        return programArea.getBlockAt(x, y);
    }

    /**
     * @return Returns true if the number of blocks in the program area is equal or greater than the maximum number of blocks
     */
    public boolean isPaletteHidden() {
        return (maxBlocks - getNumBlocksInPA()) <= 0;
    }

    /**
     * @return Returns the game world
     */
    public GameWorld getGameWorld() {
        return gameWorld;
    }

    /**
     * @return Returns the game world type
     */
    public GameWorldType getGameWorldType() {
        return gameWorldType;
    }

    /**
     * undoes the previous operation if there is one otherwise nothing will be done
     */
    public void undoCommand() {
        if (!undoStack.isEmpty()) {
            ICommand command = undoStack.pop();
            command.undo();
            redoStack.add(command);
        }
    }

    /**
     * redoes the previous undone operation if an operation is undone otherwise nothing happens
     */
    public void redoCommand() {
        if (!redoStack.isEmpty()) {
            executeCommand(redoStack.pop());
        }
    }

    /**
     * Given command will be executed
     *
     * @param command given command
     */
    public void executeCommand(ICommand command) {
        command.execute();
        undoStack.add(command);
        if (command instanceof DeleteBlockCommand || command instanceof DropBlockCommand) resetIfNeeded();
    }

    /**
     * Resets the programArea and gameworld if the program is not already in the start state
     */
    public void resetIfNeeded() {
        Block first = getProgram().isEmpty() ? null : getProgram().get(0);
        if (getProgramArea().getNextProgramBlock() != first)
            executeCommand(new ResetCommand(this));
    }

}
