package com.swop.handlers;

import com.swop.uiElements.*;
import com.swop.worldElements.Character;
import com.swop.worldElements.Square;

import java.awt.*;

public class BackendConverter {

    public UIGridElement[][] convertGrid(Square[][] bGrid, int[] goalPos) {
        int nCol = bGrid.length;
        int nRow = bGrid[0].length;
        UIGridElement[][] uiGrid = new UIGridElement[nCol][nRow];
        for (int i = 0; i < nCol; i++) {
            for (int j = 0; j < nRow; j++) {
                if (bGrid[i][j].isPassable()) {
                    if(goalPos[0] == i && goalPos[1] == j) uiGrid[i][j] = new UIGridGoal(new Point(i, j));
                    else uiGrid[i][j] = new UIGridGround(new Point(i, j));
                } else
                    uiGrid[i][j] = new UIGridWall(new Point(i, j));
            }
        }
        return uiGrid;
    }

    public UICharacter convertCharacter(Character character) {
        Directions dir;
        switch (character.getDirection()) {
            case UP:
                dir = Directions.UP;
                break;
            case RIGHT:
                dir = Directions.RIGHT;
                break;
            case DOWN:
                dir = Directions.DOWN;
                break;
            case LEFT:
                dir = Directions.LEFT;
                break;
            default:
                dir = Directions.RIGHT;
        }
        return new UICharacter(new Point(character.getPosition()[0], character.getPosition()[1]), dir);
    }
}
