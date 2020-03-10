import UIElements.*;

import java.awt.*;

public class LoadDataHandler {
    UIGameWorld uiGameWorld;
    UIGridElement[][] grid;

    public LoadDataHandler(UIGameWorld uiGameWorld) {
        this.uiGameWorld = uiGameWorld;

        //TODO: temp, replace with actual level data
        grid = new UIGridElement[5][5];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new UIGridWall(new Point(j,i));
            }
        }
        grid[4][1] = new UICharacter(new Point(1,4), Directions.RIGHT);
        grid[4][2] = new UIGridGround(new Point(2,4));
        grid[3][2] = new UIGridGround(new Point(2,3));
        grid[3][3] = new UIGridGround(new Point(3,3));
        grid[3][4] = new UIGridGround(new Point(4,3));

        initializeGameWorld();
    }

    public void initializeGameWorld(){
        //Get data

        //load data into gameworld
        uiGameWorld.setGrid(grid);
    }
}
