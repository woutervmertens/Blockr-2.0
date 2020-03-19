package com.swop.uiElements;

import java.awt.*;

public class UIGridGoal extends UIGridElement {
    public UIGridGoal(Point posInGrid) {
        super(posInGrid);
        color = new Color(0,150,50);
    }

    @Override
    public Polygon getPolygon(int size, Point gridPos) {
        Point offset = getOffset(size, gridPos);
        Polygon p = new Polygon();
        p.addPoint(offset.x + 5, offset.y + 5);
        p.addPoint(offset.x + size - 5, offset.y + 5);
        p.addPoint(offset.x + size - 5, offset.y + size - 5);
        p.addPoint(offset.x + size/2 + 1,offset.y + size - 5);
        p.addPoint(offset.x + size/2 + 1,offset.y + 2*size/3);
        p.addPoint(offset.x + 2*(size/3) + 1,offset.y + size/2);
        p.addPoint(offset.x + size/2 + 1,offset.y + size/3);
        p.addPoint(offset.x + size/2 - 1,offset.y + size/3);
        p.addPoint(offset.x + size/2 - 1,offset.y + size - 5);
        p.addPoint(offset.x + 5, offset.y + size - 5);
        return p;
    }
}
