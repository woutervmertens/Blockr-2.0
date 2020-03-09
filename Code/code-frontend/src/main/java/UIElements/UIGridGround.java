package UIElements;

import java.awt.*;

public class UIGridGround extends UIGridElement {
    public UIGridGround(Point gridPosition) {
        super(gridPosition);
        color = Color.WHITE;
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
