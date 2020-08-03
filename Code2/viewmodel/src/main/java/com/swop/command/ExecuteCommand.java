package com.swop.command;

import com.swop.*;

public class ExecuteCommand extends GameCommand {
    private ProgramAreaViewModel programAreaViewModel;
    private GameWorld gameWorld;

    public ExecuteCommand(CommandGameControllerFacade gameController, ProgramAreaViewModel programAreaViewModel, GameWorld gameWorld) {
        super(gameController);
        this.programAreaViewModel = programAreaViewModel;
        this.gameWorld = gameWorld;
    }

    @Override
    public void execute() {
        super.execute();
        gameController.setLastSuccessState(programAreaViewModel.ExecuteNext(gameWorld));
        if(gameController.getLastSuccessState() == SuccessState.FAILURE) undo();
        programAreaViewModel.setHighlight();
    }

    @Override
    public void undo() {
        super.undo();
    }

}
