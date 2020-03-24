package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.uiElements.UICharacter;
import com.swop.uiElements.UISquare;

import java.awt.*;
import java.util.ArrayList;

public class ExecuteProgramHandler {
    private BlockrGame blockrGame;

    public ExecuteProgramHandler(BlockrGame blockrGame) {
        this.blockrGame = blockrGame;
    }

    public int getNumBlocksInPA() {
        return blockrGame.getNumbBlocksInPA();
    }

    public void executeNext() {
        blockrGame.executeNext();
    }

    public void reset() {
        blockrGame.resetProgramExecution();
    }

    public UICharacter getCharacter() {

        return null;
    }

    public ArrayList<UISquare> getGameWorld(){
        blockrGame.getGameWorldGrid();

        return null;
    }
}
