package com.swop;

import com.swop.blocks.Block;
import com.swop.command.AddBlockCommand;
import com.swop.command.DeleteBlockCommand;
import com.swop.command.ExecuteCommand;
import com.swop.command.ICommand;

import java.awt.*;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

public class BlockrGame {
    private final ProgramArea programArea;
    private GameWorld gameWorld;
    private final GameWorldType gameWorldType;
    private final int maxBlocks;
    private final static AtomicReference<BlockrGame> instance = new AtomicReference<>();
    private Stack<ICommand> undoStack = new Stack<>();
    private Stack<ICommand> redoStack = new Stack<>();

    private BlockrGame(int maxBlocks,GameWorldType gameWorldType) {
        this.maxBlocks = maxBlocks;
        this.programArea = ProgramArea.getInstance();
        this.gameWorldType = gameWorldType;
        this.gameWorld = gameWorldType.createNewInstance();
    }

    public synchronized  static BlockrGame createInstance(int maxBlocks,GameWorldType gameWorldType){
        if(instance.get() == null) instance.set(new BlockrGame(maxBlocks,gameWorldType));
        return instance.get();
    }
    public synchronized static BlockrGame getInstance(){
        if(instance.get() == null) throw new IllegalStateException("BlockrGame instance used before created.");
        return instance.get();
    }

    public List<Block> getProgram() {
        return programArea.getProgram();
    }

    public Point getBlockPosition(Block block) {
        return block.getPosition();
    }

    public void dropBlockInPA(Block block) {
        if (block == null) throw new IllegalArgumentException();
        executeCommand(new AddBlockCommand(block));
    }

    public void removeBlockFromPA(Block draggedBlock) {
        executeCommand(new DeleteBlockCommand(draggedBlock));
    }

    public void removeProgramBlock(Block draggedBlock) {
        programArea.removeProgramBlock(draggedBlock);
    }

    public void executeNext() {
        executeCommand(new ExecuteCommand(programArea.getCurrentBlock()));
    }

    public Block getCurrentBlock() {
        return programArea.getCurrentBlock();
    }

    public void resetProgramExecution() {
        programArea.reset();
        gameWorld = gameWorldType.createNewInstance();
    }

    public int getNumbBlocksInPA() {
        return programArea.getAllBlocks().size();
    }

    public List<Block> getAllBlocksInPA(){return programArea.getAllBlocks();}

    public Block getBlockInPaAt(int x, int y) {
        return programArea.getBlockAt(x,y);
    }

    public boolean isPaletteHidden(){return (maxBlocks - getNumbBlocksInPA()) <= 0;}

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public void redoCommand(){
        if(!redoStack.isEmpty()){
            executeCommand(redoStack.pop());
        }
    }

    public void undoCommand(){
        if(!undoStack.isEmpty()){
            ICommand command = undoStack.pop();
            command.undo();
            redoStack.add(command);
        }
    }

    public void executeCommand(ICommand command){
        command.execute();
        undoStack.add(command);
    }

}
