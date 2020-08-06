package com.swop.command;

import com.swop.CommandGameControllerFacade;
import com.swop.blocks.BlockModel;

/**
 * The command that adds a block to the program area.
 */
public class AddBlockCommand extends GameCommand {
    private final BlockModel blockModel;

    public AddBlockCommand(CommandGameControllerFacade gameController, BlockModel blockModel) {
        super(gameController);
        this.blockModel = blockModel;
    }

    /**
     * Calls GameCommand execute and calls gameController to add the block
     */
    @Override
    public void execute() {
        super.execute();
        gameController.addBlock(blockModel);
    }
}
