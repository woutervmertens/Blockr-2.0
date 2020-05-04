package com.swop;

import com.swop.blocks.Block;
import com.swop.blocks.StatementBlock;
import com.swop.command.DeleteBlockCommand;
import com.swop.command.DropBlockCommand;
import com.swop.command.ExecuteCommand;
import com.swop.command.ICommand;

import java.awt.*;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

public class BlockrGame {
    private final static AtomicReference<BlockrGame> instance = new AtomicReference<>();
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
    private Stack<ICommand> undoStack = new Stack<>();
    /**
     * Stack for all the redo's
     */
    private Stack<ICommand> redoStack = new Stack<>();

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

    public ProgramArea getProgramArea() {return programArea; }
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
    public void dropBlockInPA(Block block) {
        if (block == null) throw new IllegalArgumentException();
        executeCommand(new DropBlockCommand(programArea, block));
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
        if (programArea.getCurrentBlock() != null) {
            executeCommand(new ExecuteCommand(gameWorld,programArea.getCurrentBlock()));
            if (programArea.getCurrentBlock().isDone()) {
                programArea.setNextCurrentBlock();
            }
        } else {
            resetEverything();
        }
    }

    public Block getCurrentActiveBlock() {
        if (programArea.getCurrentBlock() instanceof StatementBlock) {
            StatementBlock statementBlock = (StatementBlock) programArea.getCurrentBlock();

            if (((StatementBlock) programArea.getCurrentBlock()).getCurrent() != null) {
                return statementBlock.getCurrent();
            } else if (statementBlock.isConditionValid()) {
                return statementBlock.getBodyBlocks().get(0);
            }
        }
        return programArea.getCurrentBlock();
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
            if (block instanceof StatementBlock) {
                count += ((StatementBlock) block).getBodyBlocks().size() + ((StatementBlock) block).getConditions().size();
            }
            count++;
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
     * redoes the previous undone operation if an operation is undone otherwise nothing happens
     */
    public void redoCommand() {
        if (!redoStack.isEmpty()) {
            System.out.println("REDOSTACK: " + redoStack.size());
            executeCommand(redoStack.pop());
        }
    }

    /**
     * undoes the previous operation if there is one otherwise nothing will be done
     */
    public void undoCommand() {
        if (!undoStack.isEmpty()) {
            System.out.println("UNDOSTACK: " + undoStack.size());
            ICommand command = undoStack.pop();
            command.undo();
            redoStack.add(command);
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
    }

}
