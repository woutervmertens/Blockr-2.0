package com.swop.command;

import com.swop.CommandGameControllerFacade;
import com.swop.blocks.BlockModel;

public class DeleteBlockCommand extends GameCommand {
    private BlockModel blockModel;

    public DeleteBlockCommand(CommandGameControllerFacade gameController, BlockModel blockModel) {
        super(gameController);
        this.blockModel = blockModel;
    }

    /**
     * Calls GameCommand execute, and calls gameController to delete the block
     */
    @Override
    public void execute() {
        super.execute();
        gameController.deleteBlock(blockModel);
    }

}
