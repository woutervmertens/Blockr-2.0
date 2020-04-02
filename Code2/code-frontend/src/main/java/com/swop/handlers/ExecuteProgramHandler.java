package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.uiElements.UICharacter;
import com.swop.uiElements.UISquare;
import com.swop.worldElements.Character;
import com.swop.worldElements.Square;

import java.awt.*;
import java.util.ArrayList;

public class ExecuteProgramHandler {
    private BlockrGame blockrGame;
    private ArrayList<UISquare> uiGrid = null;

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
        Character robot = blockrGame.getCharacter();
        return new UICharacter(calculatePositionFromGrid(robot.getPosition(),30),robot.getDirection());
    }

    public ArrayList<UISquare> getGameWorld(){
        if(uiGrid == null) {
            Square[][] grid = blockrGame.getGameWorldGrid();
            uiGrid = new ArrayList<>();
            int elementSize = 30;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    uiGrid.add(new UISquare(new Point(calculatePositionFromGrid(new Point(i, j), elementSize)), grid[i][j]));
                }
            }
        }
        return uiGrid;
    }

    private Point calculatePositionFromGrid(Point gridPos, int elementSize){
        return new Point(gridPos.x * elementSize,gridPos.y * elementSize);
    }
}
