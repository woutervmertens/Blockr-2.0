import UIElements.UICharacter;
import UIElements.UIGridElement;

import java.awt.*;

public class UIGameWorld {
    private Point pos;
    private UIGridElement[][] grid, initialGrid;
    private int elementSize;

    public UIGameWorld(Point pos, int elementSize) {
        this.elementSize = elementSize;
        this.pos = pos;
    }

    /**
     * Sets the game world grid and a backup
     * @param grid The grid info to set
     */
    public void setGrid(UIGridElement[][] grid) {
        this.grid = grid;
        initialGrid = deepCopy(grid);
    }

    public UIGridElement[][] getGrid() {
        return grid;
    }

    public void switchElements(Point from, Point to){
        UIGridElement el = grid[from.y][from.x];
        grid[from.y][from.x] = grid[to.x][to.y];
        grid[to.x][to.y] = el;
        Point p = grid[to.x][to.y].getPosInGrid();
        grid[to.x][to.y].setPosInGrid(grid[from.y][from.x].getPosInGrid());
        grid[from.y][from.x].setPosInGrid(p);
    }

    /**
     * For each grid element, draws the polygon in the elements color.
     * @param g awt Graphics
     */
    public void draw(Graphics g) {
        if (grid == null) return;
        for (UIGridElement[] elCol : grid) {
            for (UIGridElement el : elCol) {
                g.setColor(el.getColor());
                g.drawPolygon(el.getPolygon(elementSize, pos));
            }
        }
    }

    /**
     * Reset grid to backup
     */
    public void Reset() {
        this.grid = deepCopy(initialGrid);
    }

    public UICharacter getCharacter()
    {
        for (UIGridElement[] elCol : grid) {
            for (UIGridElement el : elCol) {
                if(el instanceof UICharacter)
                    return (UICharacter) el;
            }
        }
        return null;
    }

    private <T> T[][] deepCopy(T[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }
}
