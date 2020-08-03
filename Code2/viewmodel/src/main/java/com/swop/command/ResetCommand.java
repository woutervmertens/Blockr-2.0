package com.swop.command;

import com.swop.CommandGameControllerFacade;

public class ResetCommand extends GameCommand{
    public ResetCommand(CommandGameControllerFacade gameController) {
        super(gameController);
    }

    @Override
    public void execute() {
        super.execute();
        gameController.resetExecution();
    }

    @Override
    public void undo() {
        super.undo();
    }
}
