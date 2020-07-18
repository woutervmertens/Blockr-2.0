package com.swop.command;

import com.swop.GameController;
import com.swop.GameWorld;
import com.swop.ProgramAreaViewModel;

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
        programAreaViewModel.ExecuteNext(gameWorld);
    }

    @Override
    public void undo() {
        super.execute();
    }

}
