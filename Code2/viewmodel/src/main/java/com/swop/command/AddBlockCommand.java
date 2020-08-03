package com.swop.command;

import com.swop.CommandGameControllerFacade;
import com.swop.blocks.BlockModel;


public class AddBlockCommand extends GameCommand {
    private final BlockModel blockModel;

    public AddBlockCommand(CommandGameControllerFacade gameController, BlockModel blockModel) {
        super(gameController);
        this.blockModel = blockModel;
    }

    @Override
    public void execute() {
        super.execute();
        gameController.addBlock(blockModel);
    }

    @Override
    public void undo() {
        super.undo();
    }
}
