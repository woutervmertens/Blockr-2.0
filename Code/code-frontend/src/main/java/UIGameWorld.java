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

    public void setGrid(UIGridElement[][] grid) {
        this.grid = grid;
        initialGrid = grid;
    }

    public void draw(Graphics g) {
        if (grid == null) return;
        for (UIGridElement[] elCol : grid) {
            for (UIGridElement el : elCol) {
                g.setColor(el.getColor());
                g.drawPolygon(el.getPolygon(elementSize, pos));
            }
        }
    }

    public void Reset() {
        grid = initialGrid;
    }
}
