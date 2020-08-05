package com.swop.command;

import com.swop.CommandGameControllerFacade;

public class ResetCommand extends GameCommand{
    public ResetCommand(CommandGameControllerFacade gameController) {
        super(gameController);
    }

    /**
     * Calls GameCommand execute and calls to reset execution
     */
    @Override
    public void execute() {
        super.execute();
        gameController.resetExecution();
    }
}
