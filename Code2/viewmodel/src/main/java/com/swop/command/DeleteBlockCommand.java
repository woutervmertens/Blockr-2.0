package com.swop.command;

import com.swop.CommandGameControllerFacade;
import com.swop.blocks.BlockModel;

public class DeleteBlockCommand extends GameCommand {
    private BlockModel blockModel;

    public DeleteBlockCommand(CommandGameControllerFacade gameController, BlockModel blockModel) {
        super(gameController);
        this.blockModel = blockModel;
    }

    public void execute() {
        super.execute();
        gameController.deleteBlock(blockModel);
    }

    public void undo() {
        super.undo();
    }
}
