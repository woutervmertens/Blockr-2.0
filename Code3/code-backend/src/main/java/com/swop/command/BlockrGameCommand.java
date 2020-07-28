package com.swop.command;

import com.swop.BlockrGame;

public abstract class BlockrGameCommand implements ICommand {
    protected BlockrGame blockrGame;

    public BlockrGameCommand(BlockrGame blockrGame) {
        this.blockrGame = blockrGame;
    }
}
