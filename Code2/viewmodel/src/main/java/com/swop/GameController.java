package com.swop;

import com.swop.blocks.BlockVM;
import com.swop.blocks.BlockFactory;
import com.swop.blocks.BlockModel;
import com.swop.blocks.StdBlockData;
import com.swop.command.*;

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

    private BlockVM draggedBlockVM = null;

    private SuccessState lastSuccessState = SuccessState.SUCCESS;

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
        RepaintEventController.getInstance().CallRepaint();
    }

    public void CallReleaseInVms(int x, int y){
        for (ViewModel vm : viewModels){
            vm.HandleMouseRelease(x,y);
        }
    }

    public void HandleMouseDrag(int x, int y){
        if(draggedBlockVM != null)
            draggedBlockVM.updatePosition(new Point(x,y));
        else {
            for (ViewModel vm : viewModels) {
                vm.HandleMouseDrag(x, y);
            }
        }
        RepaintEventController.getInstance().CallRepaint();
    }

    public int getNrBlocksAvailable() {
        return 20 - programAreaVM.getNumBlocksUsed();
    }

    /**
     * Undoes the previous operation if there is one otherwise nothing will be done.
     */
    public void undoCommand() {
        if (!undoStack.isEmpty()) {
            ICommand command = undoStack.pop();
            command.undo();
            redoStack.add(command);
            RepaintEventController.getInstance().CallRepaint();
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
            RepaintEventController.getInstance().CallRepaint();
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

    public void callResetCommand(){
        executeCommand(new ResetCommand(this));
    }

    public void resetExecution() {
        for (ViewModel vm : viewModels){
            vm.HandleReset();
        }
        setLastSuccessState(SuccessState.FAILURE);
        RepaintEventController.getInstance().CallRepaint();
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

    public BlockVM getDraggedBlockVM(){
        return draggedBlockVM;
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
        programAreaVM.RemoveBlock(blockModel);
        paletteVM.reactToBlockRemove(blockModel);
        gameWorldVM.HandleReset();
        programAreaVM.HandleReset();
    }

    public void addBlock(BlockModel blockModel) {
        programAreaVM.DropBlock(blockModel);
        paletteVM.reactToBlockCreate(blockModel);
        gameWorldVM.HandleReset();
        programAreaVM.HandleReset();
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
        gameWorldVM.setGameWorld(gameWorldType.createNewInstance(), gameWorldType);
        addViewModel(this.gameWorldVM);
    }

    public void dropDraggedBlock() {
        if(draggedBlockVM == null) return;
        if(programAreaVM.isWithin(draggedBlockVM.getPosition().x, draggedBlockVM.getPosition().y))
        {
            executeCommand(new AddBlockCommand(this, draggedBlockVM.getModel()));
        }
        else
            executeCommand(new DeleteBlockCommand(this,draggedBlockVM.getModel()));
        draggedBlockVM = null;
    }

    public void setDraggedBlockVM(BlockModel bm) {
        if(bm == null) {
            draggedBlockVM = null;
            return;
        }
        draggedBlockVM = BlockFactory.getInstance().createBlockVM(bm);

    }

    public SuccessState getLastSuccessState() {
        return lastSuccessState;
    }

    public void setLastSuccessState(SuccessState lastSuccessState) {
        this.lastSuccessState = lastSuccessState;
    }
}
