package com.swop.command;

import com.swop.*;

/**
 * The command that execute a block in the program area.
 */
public class ExecuteCommand extends GameCommand {
    private ProgramAreaViewModel programAreaViewModel;
    private GameWorld gameWorld;

    public ExecuteCommand(CommandGameControllerFacade gameController, ProgramAreaViewModel programAreaViewModel, GameWorld gameWorld) {
        super(gameController);
        this.programAreaViewModel = programAreaViewModel;
        this.gameWorld = gameWorld;
    }

    /**
     * Calls GameCommand execute, execute next in the program area VM and handles illegal actions and calls on program area VM to handle the highlight
     */
    @Override
    public void execute() {
        super.execute();
        gameController.setLastSuccessState(programAreaViewModel.ExecuteNext(gameWorld));
        if(gameController.getLastSuccessState() == SuccessState.FAILURE) undo();
        programAreaViewModel.setHighlight();
    }

}
