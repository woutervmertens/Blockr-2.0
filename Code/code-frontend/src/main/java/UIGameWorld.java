import UIElements.UIGridElement;

import java.awt.*;

public class UIGameWorld {
    private UIGridElement[][] grid;
    private int elementSize;

    public void draw(Graphics g, Point pos)
    {
        for (UIGridElement[] elCol : grid)
        {
            for (UIGridElement el : elCol){
                g.setColor(el.getColor());
                g.drawPolygon(el.getPolygon(elementSize,pos));
            }
        }
    }
}
