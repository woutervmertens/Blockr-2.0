package com.swop.command;

import com.swop.GameController;
import com.swop.GameSnapshot;
import com.swop.blocks.BlockModel;

import java.awt.*;

public class DeleteBlockCommand extends GameCommand {
    private BlockModel blockModel;

    public DeleteBlockCommand(GameController gameController, BlockModel blockModel) {
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
