package UIElements;

import java.awt.*;

public class UIGridGround extends UIGridElement {
    public UIGridGround(Point posInGrid) {
        super(posInGrid);
        color = Color.lightGray;
    }

    @Override
    public Polygon getPolygon(int size, Point gridPos) {
        Point offset = getOffset(size, gridPos);
        Polygon p = new Polygon();
        p.addPoint(offset.x + 5, offset.y + 5);
        p.addPoint(offset.x + size - 5, offset.y + 5);
        p.addPoint(offset.x + size - 5, offset.y + size - 5);
        p.addPoint(offset.x + 5, offset.y + size - 5);
        return p;
    }
}
