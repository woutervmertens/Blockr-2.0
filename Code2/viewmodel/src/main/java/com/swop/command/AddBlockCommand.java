package com.swop.command;

import com.swop.GameController;
import com.swop.blocks.BlockModel;

import java.awt.*;

public class AddBlockCommand extends GameCommand {
    private final BlockModel blockModel;

    public AddBlockCommand(GameController gameController, BlockModel blockModel) {
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
        super.execute();
    }
}
