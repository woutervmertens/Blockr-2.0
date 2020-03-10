import UIElements.UIGridElement;

import java.awt.*;

public class UIGameWorld {
    private UIGridElement[][] grid;
    private int elementSize;

    public UIGameWorld(int elementSize) {
        this.elementSize = elementSize;
    }

    public void setGrid(UIGridElement[][] grid) {
        this.grid = grid;
    }

    public void draw(Graphics g, Point pos)
    {
        if(grid == null) return;
        for (UIGridElement[] elCol : grid)
        {
            for (UIGridElement el : elCol){
                g.setColor(el.getColor());
                g.drawPolygon(el.getPolygon(elementSize,pos));
            }
        }
    }
}
