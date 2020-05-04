package com.swop.command;

import com.swop.GameWorld;

public abstract class GameWorldCommand implements ICommand {
    protected GameWorld gameWorld;

    public GameWorldCommand(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }
}
