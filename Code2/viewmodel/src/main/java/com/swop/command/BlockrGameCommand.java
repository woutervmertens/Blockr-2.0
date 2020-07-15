package com.swop.command;

public abstract class BlockrGameCommand implements ICommand {
    protected BlockrGame blockrGame;

    public BlockrGameCommand(BlockrGame blockrGame) {
        this.blockrGame = blockrGame;
    }
}
