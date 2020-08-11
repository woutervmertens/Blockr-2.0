package com.swop;

import com.swop.blocks.BlockVM;
import com.swop.blocks.BlockFactory;
import com.swop.blocks.BlockModel;
import com.swop.command.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

/**
 * The main controller, holds undo/redo stacks and directs commands and data between classes.
 */
public class GameController {
    private ViewModelFacade viewModelFacade;
    private GameWorldType gameWorldType;

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
        viewModelFacade = new ViewModelFacade();
    }

    /**
     * Calls HandleMousePress for every ViewModel and flags for repaint.
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
    public void HandleMousePress(int x, int y){
        viewModelFacade.ChainMousePress(x,y);
        RepaintEventController.getInstance().CallRepaint();
    }

    /**
     * Calls HandleMouseRelease for every ViewModel.
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
    public void CallReleaseInVms(int x, int y){
        viewModelFacade.ChainMouseRelease(x,y);
        setDraggedBlockVM(null);
    }

    /**
     * Changes the position of the dragged block or calls HandleMouseDrag for every ViewModel and flags for repaint.
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
    public void HandleMouseDrag(int x, int y){
        if(draggedBlockVM != null)
            draggedBlockVM.updatePosition(new Point(x,y));
        else {
            viewModelFacade.ChainMouseDrag(x,y);
        }
        RepaintEventController.getInstance().CallRepaint();
    }

    public int getNrBlocksAvailable() {
        return 20 - viewModelFacade.getProgramAreaVM().getNumBlocksUsed();
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

    /**
     * Calls execute on a new Execute Command for the next Block in the Program
     */
    public void executeNext() {
        executeCommand(new ExecuteCommand(new CommandGameControllerFacade(this),viewModelFacade.getProgramAreaVM(), viewModelFacade.getGameWorldVM().getGameWorld()));
    }

    /**
     * Calls execute on a new Reset Command
     */
    public void callResetCommand(){
        executeCommand(new ResetCommand(new CommandGameControllerFacade(this)));
    }

    /**
     * Calls HandleReset in every ViewModel, flags for repaint.
     */
    public void resetExecution() {
        viewModelFacade.ChainReset();
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

    public BlockVM getDraggedBlockVM(){
        return draggedBlockVM;
    }

    /**
     * Creates a Memento GameSnapshot for the current state of every ViewModel.
     * @return the GameSnapshot of the current state.
     */
    public GameSnapshot createSnapshot() {
        return new GameSnapshot(viewModelFacade.getGameWorldVM().getGameWorld().createSnapshot(),viewModelFacade.getPaletteVM().getModel(),viewModelFacade.getProgramAreaVM().getModel());
    }

    /**
     * Restores the state of the given GameSnapshot Memento.
     * @param snapshot the GameSnapshot to restore to.
     */
    public void restoreSnapshot(GameSnapshot snapshot) {
        viewModelFacade.getGameWorldVM().getGameWorld().restoreSnapshot(snapshot.getGameWorldSnapshot());
        viewModelFacade.getPaletteVM().setModel(snapshot.getPaletteModel());
        viewModelFacade.getProgramAreaVM().setModel(snapshot.getProgramAreaModel());
    }

    /**
     * Calls to remove the given BlockModel from the ProgramArea, calls for a reaction from the Palette and resets GameWorld and ProgramArea.
     * @param blockModel the BlockModel to remove.
     */
    public void deleteBlock(BlockModel blockModel) {
        viewModelFacade.getProgramAreaVM().RemoveBlock(blockModel);
        viewModelFacade.getPaletteVM().reactToBlockRemove(blockModel);
        viewModelFacade.getGameWorldVM().HandleReset();
        viewModelFacade.getProgramAreaVM().HandleReset();
    }

    /**
     * Calls to add the given BlockModel to the ProgramArea, calls for a reaction from the Palette and resets GameWorld and ProgramArea.
     * @param blockModel the BlockModel to add.
     */
    public void addBlock(BlockModel blockModel) {
        viewModelFacade.getProgramAreaVM().DropBlock(blockModel);
        viewModelFacade.getPaletteVM().reactToBlockCreate(blockModel);
        viewModelFacade.getGameWorldVM().HandleReset();
        viewModelFacade.getProgramAreaVM().HandleReset();
    }

    public void setPaletteVM(PaletteViewModel paletteVM) {
        viewModelFacade.setPaletteVM(paletteVM);
    }

    public void setProgramAreaVM(ProgramAreaViewModel programAreaVM) {
        viewModelFacade.setProgramAreaVM(programAreaVM);
    }

    public void setGameWorldVM(GameWorldViewModel gameWorldVM) {
        gameWorldVM.setGameWorld(gameWorldType.createNewInstance(), gameWorldType);
        viewModelFacade.setGameWorldVM(gameWorldVM);
    }

    /**
     * Drops the BlockModel being dragged at the current position and calls the appropriate Commands.
     */
    public void dropDraggedBlock() {
        if(draggedBlockVM == null) return;
        if(viewModelFacade.getProgramAreaVM().isWithin(draggedBlockVM.getPosition().x, draggedBlockVM.getPosition().y))
        {
            executeCommand(new AddBlockCommand(new CommandGameControllerFacade(this), draggedBlockVM.getModel()));
        }
        else
            executeCommand(new DeleteBlockCommand(new CommandGameControllerFacade(this),draggedBlockVM.getModel()));
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
