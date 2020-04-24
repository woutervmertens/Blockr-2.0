package com.swop.command;

import com.swop.BlockrGame;
import com.swop.GameWorld;

public abstract class GameWorldCommand implements ICommand {
    protected GameWorld gameWorld;

    public GameWorldCommand() {
        gameWorld = BlockrGame.getInstance().getGameWorld();
    }
}
