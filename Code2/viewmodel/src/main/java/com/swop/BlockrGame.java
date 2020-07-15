package com.swop;

import com.swop.blocks.*;
import com.swop.command.*;

import java.awt.*;
import java.util.List;
import java.util.Stack;

public class BlockrGame {
    //private final ProgramArea programArea;
    //private final GameWorldType gameWorldType;
    ///**
    // * Maximum number of blocks that can be used
    // */
    //private final int maxBlocks;
    //private GameWorld gameWorld;
    ///**
    // * Stack for all the undo's
    // */
    //private final Stack<ICommand> undoStack = new Stack<>();
    ///**
    // * Stack for all the redo's
    // */
    //private final Stack<ICommand> redoStack = new Stack<>();
//
    ///**
    // * Initializes BlockrGame
    // * @param maxBlocks     Maximum number of blocks that can be used.
    // * @param gameWorldType Given type of gameWorld.
    // */
    //public BlockrGame(int maxBlocks, GameWorldType gameWorldType) {
    //    this.maxBlocks = maxBlocks;
    //    this.programArea = new ProgramArea();
    //    this.gameWorldType = gameWorldType;
    //    this.gameWorld = gameWorldType.createNewInstance();
//
    //}
//
    ///**
    // * @return Returns program that is currently in program area
    // */
    //public List<BlockModel> getProgram() {
    //    return programArea.getProgram();
    //}
//
    //public ProgramArea getProgramArea() {
    //    return programArea;
    //}
//
    ///**
    // * @param blockModel Given block
    // * @return Returns the position of the given block
    // */
    //public Point getBlockPosition(BlockModel blockModel) {
    //    return blockModel.getPosition();
    //}
//
    ///**
    // * Adds block to Program Area
    // * @param blockModel given block
    // * @param x x position
    // * @param y y position
    // */
    //public void dropBlockInPAAt(BlockModel blockModel, int x, int y) {
    //    if (blockModel == null) throw new IllegalArgumentException();
    //    executeCommand(new DropBlockCommand(programArea, blockModel, x, y));
    //    resetIfNeeded();
    //}
//
    ///**
    // * Removes block from Program Area
    // * @param draggedBlockModel given block
    // * @param isRelease Boolean recording whether this is a release (outside PA) or not (a click)
    // */
    //public void removeBlockFromPA(BlockModel draggedBlockModel, boolean isRelease) {
    //    if (isRelease) {
    //        executeCommand(new DeleteBlockCommand(programArea, draggedBlockModel));
    //    } else {
    //        programArea.removeBlockFromPA(draggedBlockModel);
    //    }
    //}
//
    ///**
    // * Executes next block
    // */
    //public void executeNext() {
    //    if (programArea.getNextProgramBlockModel() != null) {
    //        executeCommand(new ExecuteCommand(this, programArea.getNextProgramBlockModel()));
    //    } else {
    //        executeCommand(new ResetCommand(this));
    //    }
    //}
//
    ///**
    // * Get next effectively to be executed block for highlight.
    // * @return A Block to highlight.
    // */
    //public BlockModel getToHighlightBlock() {
    //    BlockModel toHighlightBlockModel = programArea.getNextProgramBlockModel();
//
    //    if (toHighlightBlockModel instanceof FunctionCallBlockModel) {
    //        toHighlightBlockModel = ((FunctionCallBlockModel) toHighlightBlockModel).getDefinitionBlock();
    //    }
//
    //    while (toHighlightBlockModel instanceof BlockModelWithBody || toHighlightBlockModel instanceof FunctionCallBlockModel) {
    //        if (toHighlightBlockModel instanceof FunctionCallBlockModel) {
    //            toHighlightBlockModel = ((FunctionCallBlockModel) toHighlightBlockModel).getDefinitionBlock();
    //        }
    //        if (((BlockModelWithBody) toHighlightBlockModel).getNextBodyBlockModel() != null) {
    //            toHighlightBlockModel = ((BlockModelWithBody) toHighlightBlockModel).getNextBodyBlockModel();
    //        } else if (! ((BlockModelWithBody) toHighlightBlockModel).getBodyBlockModels().isEmpty()) {
    //            if (toHighlightBlockModel instanceof StatementBlockModel) {
    //                if (((StatementBlockModel) toHighlightBlockModel).isConditionValid()) {
    //                    toHighlightBlockModel = ((StatementBlockModel) toHighlightBlockModel).getBodyBlockModels().get(0);
    //                } else {
    //                    break;
    //                }
    //            } else {  // function definition
    //                toHighlightBlockModel = ((BlockModelWithBody) toHighlightBlockModel).getBodyBlockModels().get(0);
    //            }
    //        } else {
    //            break;
    //        }
    //    }
//
    //    return toHighlightBlockModel;
    //}
//
    ///**
    // * Resets the game world and the program area
    // */
    //public void resetEverything() {
    //    programArea.resetProgramExecution();
    //    gameWorld = gameWorldType.createNewInstance();
    //}
//
    ///**
    // * @return Returns the number of blocks that are in the Program Area
    // */
    //public int getNumBlocksInPA() {
    //    return programArea.getAllBlockModels().size();
    //}
//
    ///**
    // * @return Returns the number of Blocks in the Program Area.
    // */
    //public int getNumBlocksInProgram() {
    //    int count = 0;
    //    if (getProgram().isEmpty() || getProgram().get(0) == null) return count;
    //    for (BlockModel blockModel : getProgram()) {
    //        count += blockModel.getCount();
    //    }
    //    return count;
    //}
//
    ///**
    // * @return Returns all the blocks that are in the Program Area
    // */
    //public List<BlockModel> getAllBlocksInPA() {
    //    return programArea.getAllBlockModels();
    //}
//
    ///**
    // * position of a block is (x,y)
    // * @param x x-value of position
    // * @param y y-value of position
    // * @return Returns a block if there is one at the given position otherwise null will be returned.
    // */
    //public BlockModel getBlockInPaAt(int x, int y) {
    //    return programArea.getBlockAt(x, y);
    //}
//
    ///**
    // * @return Returns true if the number of blocks in the program area is equal or greater than the maximum number of blocks
    // */
    //public boolean isPaletteHidden() {
    //    return (maxBlocks - getNumBlocksInPA()) <= 0;
    //}
//
    ///**
    // * @return Returns the game world.
    // */
    //public GameWorld getGameWorld() {
    //    return gameWorld;
    //}
//
    ///**
    // * @return Returns the game world type.
    // */
    //public GameWorldType getGameWorldType() {
    //    return gameWorldType;
    //}
//
    ///**
    // * Undoes the previous operation if there is one otherwise nothing will be done.
    // */
    //public void undoCommand() {
    //    if (!undoStack.isEmpty()) {
    //        ICommand command = undoStack.pop();
    //        command.undo();
    //        redoStack.add(command);
    //    }
    //}
//
    ///**
    // * Redoes the previous undone operation if an operation is undone otherwise nothing happens.
    // */
    //public void redoCommand() {
    //    if (!redoStack.isEmpty()) {
    //        Stack<ICommand> redoBackup = new Stack<>();
    //        redoBackup.addAll(redoStack);
//
    //        executeCommand(redoStack.pop());
//
    //        redoStack.addAll(redoBackup);
    //        redoStack.pop();
    //    }
    //}
//
    ///**
    // * Given command will be executed.
    // * @param command given command
    // */
    //public void executeCommand(ICommand command) {
    //    command.execute();
    //    undoStack.add(command);
    //    redoStack.clear();
    //}
//
    ///**
    // * Resets the programArea and gameWorld if the program is not already in the start state.
    // */
    //public void resetIfNeeded() {
    //    BlockModel first = getProgram().isEmpty() ? null : getProgram().get(0);
    //    if (getProgramArea().getNextProgramBlockModel() != first)
    //        executeCommand(new ResetCommand(this));
    //}

}
