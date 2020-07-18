package com.swop;

import com.swop.blocks.Block;
import com.swop.blocks.BlockModel;
import com.swop.blocks.StdBlockData;
import com.swop.command.ExecuteCommand;
import com.swop.command.ICommand;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class GameController {
    private ArrayList<ViewModel> viewModels;
    private PaletteViewModel paletteVM;
    private ProgramAreaViewModel programAreaVM;
    private GameWorldViewModel gameWorldVM;
    private GameWorldType gameWorldType;

    private StdBlockData defaultActionData;
    private StdBlockData defaultPredicateData;
    private StdBlockData defaultBodyBlockData;

    /**
     * Stack for all the undo's
     */
    private final Stack<ICommand> undoStack = new Stack<>();
    /**
     * Stack for all the redo's
     */
    private final Stack<ICommand> redoStack = new Stack<>();

    public GameController(GameWorldType gameWorldType){
        this.gameWorldType = gameWorldType;
        this.viewModels = new ArrayList<>();
        defaultActionData = new StdBlockData(
                new Point(0,0),
                110,
                30,
                ""
        );
        defaultPredicateData = new StdBlockData(
                new Point(0,0),
                40,
                30,
                ""
        );
        defaultBodyBlockData = new StdBlockData(
                new Point(0,0),
                70,
                30,
                30,
                10,
                0,
                ""
        );
    }

    private void addViewModel(ViewModel newVM){
        viewModels.add(newVM);
    }

    public void HandleMousePress(int x, int y){
        for (ViewModel vm : viewModels){
            vm.HandleMousePress(x,y);
        }
    }

    public void HandleMouseRelease(Block draggedBlock, int x, int y){
        for (ViewModel vm : viewModels){
            vm.HandleMouseRelease(draggedBlock,x,y);
        }
    }

    public void HandleMouseDrag(int x, int y){
        for (ViewModel vm : viewModels){
            vm.HandleMouseDrag(x,y);
        }
    }

    public int getNrBlocksAvailable() {
        return 20;
    }

    /**
     * Undoes the previous operation if there is one otherwise nothing will be done.
     */
    public void undoCommand() {
        if (!undoStack.isEmpty()) {
            ICommand command = undoStack.pop();
            command.undo();
            redoStack.add(command);
        }
    }

    /**
     * Redoes the previous undone operation if an operation is undone otherwise nothing happens.
     */
    public void redoCommand() {
        if (!redoStack.isEmpty()) {
            Stack<ICommand> redoBackup = new Stack<>();
            redoBackup.addAll(redoStack);

            executeCommand(redoStack.pop());

            redoStack.addAll(redoBackup);
            redoStack.pop();
        }
    }

    /**
     * Given command will be executed.
     * @param command given command
     */
    public void executeCommand(ICommand command) {
        command.execute();
        undoStack.add(command);
        redoStack.clear();
    }

    public void executeNext() {
        executeCommand(new ExecuteCommand(this,programAreaVM, gameWorldVM.getGameWorld()));
    }

    public void resetExecution() {
        for (ViewModel vm : viewModels){
            vm.HandleReset();
        }
    }

    public Collection<Action> getSupportedActions()
    {
        return gameWorldType.getSupportedActions();
    }

    public Collection<Predicate> getSupportedPredicates(){
        return gameWorldType.getSupportedPredicates();
    }


    public StdBlockData getDefaultActionData() {
        return defaultActionData;
    }

    public StdBlockData getDefaultPredicateData() {
        return defaultPredicateData;
    }

    public StdBlockData getDefaultBodyBlockData() {
        return defaultBodyBlockData;
    }

    public String getFeedback() {
        return "";
    }

    public GameSnapshot createSnapshot() {
        return new GameSnapshot(gameWorldVM.getGameWorld().createSnapshot(),paletteVM.getModel(),programAreaVM.getModel());
    }

    public void restoreSnapshot(GameSnapshot snapshot) {
        gameWorldVM.getGameWorld().restoreSnapshot(snapshot.getGameWorldSnapshot());
        paletteVM.setModel(snapshot.getPaletteModel());
        programAreaVM.setModel(snapshot.getProgramAreaModel());
    }

    public void deleteBlock(BlockModel blockModel) {
    }

    public void addBlock(BlockModel blockModel) {
    }

    public void setPaletteVM(PaletteViewModel paletteVM) {
        this.paletteVM = paletteVM;
        addViewModel(this.paletteVM);
    }

    public void setProgramAreaVM(ProgramAreaViewModel programAreaVM) {
        this.programAreaVM = programAreaVM;
        addViewModel(this.programAreaVM);
    }

    public void setGameWorldVM(GameWorldViewModel gameWorldVM) {
        this.gameWorldVM = gameWorldVM;
        gameWorldVM.setGameWorld(gameWorldType.createNewInstance());
        addViewModel(this.gameWorldVM);
    }
}
