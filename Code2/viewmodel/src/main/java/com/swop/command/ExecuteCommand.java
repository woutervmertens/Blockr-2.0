package com.swop.command;

import com.swop.GameController;
import com.swop.GameWorld;
import com.swop.ProgramAreaViewModel;
import com.swop.SuccessState;

public class ExecuteCommand extends GameCommand {
    private ProgramAreaViewModel programAreaViewModel;
    private GameWorld gameWorld;

    public ExecuteCommand(GameController gameController, ProgramAreaViewModel programAreaViewModel, GameWorld gameWorld) {
        super(gameController);
        this.programAreaViewModel = programAreaViewModel;
        this.gameWorld = gameWorld;
    }

    @Override
    public void execute() {
        super.execute();
        gameController.setLastSuccessState(programAreaViewModel.ExecuteNext(gameWorld));
        if(gameController.getLastSuccessState() == SuccessState.FAILURE) undo();
    }

    @Override
    public void undo() {
        super.undo();
    }

}
