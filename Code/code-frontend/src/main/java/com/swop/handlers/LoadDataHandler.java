package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.windowElements.UIGameWorld;

/**
 * TODO: remove the blockrGame dependency and move it to com.swop.handlers package
 */
public class LoadDataHandler {
    public LoadDataHandler(BlockrGame blockrGame, UIGameWorld uiGameWorld) {
        //Get data
        BackendConverter converter = new BackendConverter();
        //load data into gameworld
        uiGameWorld.setGrid(converter.convertGrid(blockrGame.getGrid()));
        uiGameWorld.setUiCharacter(converter.convertCharacter(blockrGame.getCharacter()));
    }

}