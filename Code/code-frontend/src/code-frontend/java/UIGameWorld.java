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
        initialGrid = grid;
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
        grid = initialGrid;
    }
}
