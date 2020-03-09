package UIElements;

import java.awt.*;

public class UIGridWall extends UIGridElement {
    public UIGridWall(Point gridPosition) {
        super(gridPosition);
        color = Color.DARK_GRAY;
    }

    @Override
    public Polygon getPolygon(int size) {
        Polygon p = new Polygon();
        p.addPoint(5,5);
        p.addPoint(size - 5,5);
        p.addPoint(size - 5,size - 5);
        p.addPoint(5, size - 5);
        return p;
    }
}
