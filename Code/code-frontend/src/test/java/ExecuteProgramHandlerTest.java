import UIElements.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.BeforeClass;

import java.awt.*;

import static junit.framework.TestCase.assertTrue;

public class ExecuteProgramHandlerTest {
    private static UIGridElement[][] grid;
    private static UIPalette uiPalette;
    private static UIProgramArea uiProgramArea;
    private static UIGameWorld uiGameWorld;

    @BeforeClass
    public static void beforeClass() throws Exception {
        //set up game world
        uiPalette = new UIPalette(new Point(0, 0), 600 / 4, 600, 30);
        uiProgramArea = new UIProgramArea(new Point(uiPalette.getWidth(), 0), 600 / 2, 600);
        uiGameWorld = new UIGameWorld(new Point(uiPalette.getWidth() + uiProgramArea.getWidth(), 0), 30);

        grid = new UIGridElement[5][5];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new UIGridWall(new Point(j, i));
            }
        }
        grid[4][1] = new UICharacter(new Point(1, 4), Directions.RIGHT);
        grid[4][2] = new UIGridGround(new Point(2, 4));
        grid[3][2] = new UIGridGround(new Point(2, 3));
        grid[3][3] = new UIGridGround(new Point(3, 3));
        grid[3][4] = new UIGridGround(new Point(4, 3));

        uiGameWorld.setGrid(grid);
    }

    @Test
    public void turnCharacter() {
        ExecuteProgramHandler tester = new ExecuteProgramHandler(uiProgramArea,uiGameWorld);
        UIGridElement[][] grid2 = new UIGridElement[5][5];
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[i].length; j++) {
                grid2[i][j] = new UIGridWall(new Point(j, i));
            }
        }
        grid2[4][1] = new UICharacter(new Point(1, 4), Directions.DOWN);
        grid2[4][2] = new UIGridGround(new Point(2, 4));
        grid2[3][2] = new UIGridGround(new Point(2, 3));
        grid2[3][3] = new UIGridGround(new Point(3, 3));
        grid2[3][4] = new UIGridGround(new Point(4, 3));

        tester.turnCharacter(Directions.DOWN);

        try{
            assertTrue(compareGrid(grid,grid2));
        }catch (Exception e){
            Assert.fail("Exception " + e);
        }
    }

    @Test
    public void moveCharacter() {
        ExecuteProgramHandler tester = new ExecuteProgramHandler(uiProgramArea,uiGameWorld);
        UIGridElement[][] grid2 = new UIGridElement[5][5];
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[i].length; j++) {
                grid2[i][j] = new UIGridWall(new Point(j, i));
            }
        }

        grid2[4][1] = new UIGridGround(new Point(1, 4));
        grid2[4][2] = new UICharacter(new Point(2, 4), Directions.RIGHT);
        grid2[3][2] = new UIGridGround(new Point(2, 3));
        grid2[3][3] = new UIGridGround(new Point(3, 3));
        grid2[3][4] = new UIGridGround(new Point(4, 3));

        tester.moveCharacter(4,2);

        try {
            assertTrue(compareGrid(grid,grid2));
        }catch (Exception e){
            Assert.fail("Exception " + e);
        }
    }

    @Test
    public void reset()
    {
        ExecuteProgramHandler tester = new ExecuteProgramHandler(uiProgramArea,uiGameWorld);
        UIGridElement[][] grid2 = new UIGridElement[5][5];
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[i].length; j++) {
                grid2[i][j] = new UIGridWall(new Point(j, i));
            }
        }

        grid2[4][1] = new UICharacter(new Point(1, 4), Directions.RIGHT);
        grid2[4][2] = new UIGridGround(new Point(2, 4));
        grid2[3][2] = new UIGridGround(new Point(2, 3));
        grid2[3][3] = new UIGridGround(new Point(3, 3));
        grid2[3][4] = new UIGridGround(new Point(4, 3));

        tester.moveCharacter(4,2);
        tester.reset();
        UIGridElement[][] g3 = uiGameWorld.getGrid();

        boolean t1 = compareGrid(g3,grid2);
        boolean t2 = compareGrid(g3,grid);

        try {
            assertTrue(compareGrid(grid,grid2));
        }catch (Exception e){
            Assert.fail("Exception " + e);
        }
    }

    private boolean compareGrid(UIGridElement[][] gridCopy, UIGridElement[][] grid2)
    {
        boolean same = true;
        for (int i = 0; i < gridCopy.length; i++) {
            for (int j = 0; j < gridCopy[i].length; j++) {
                if(gridCopy[i][j].getClass() != grid2[i][j].getClass()
                        || gridCopy[i][j].getPosInGrid().x != grid2[i][j].getPosInGrid().x
                        || gridCopy[i][j].getPosInGrid().y != grid2[i][j].getPosInGrid().y){
                    same = false;
                    break;
                }
                if(gridCopy[i][j] instanceof UICharacter)
                {
                    if(((UICharacter)gridCopy[i][j]).getDirection() != ((UICharacter)grid2[i][j]).getDirection())
                    {
                        same = false;
                        break;
                    }
                }
            }
            if(!same) break;
        }
        return same;
    }
}