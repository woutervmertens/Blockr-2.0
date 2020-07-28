package com.swop.command;

import com.swop.GameController;

public class ResetCommand extends GameCommand{
    public ResetCommand(GameController gameController) {
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
