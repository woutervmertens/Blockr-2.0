package com.swop.blocks;

import java.awt.*;

public enum ConnectorType {
    TOP,
    BOTTOM,
    LEFT,
    RIGHT,
    INNER_TOP,
    INNER_BOTTOM;

    public Point getOffset(StdBlockData data){
        switch (this){
            case BOTTOM:
                return new Point(0,data.getHeight());
            case RIGHT:
                return new Point(data.getWidth(),0);
            case INNER_TOP:
                return new Point(data.getPillarWidth(),data.getTitleHeight());
            case INNER_BOTTOM:
                return new Point(data.getPillarWidth(),data.getTitleHeight() + data.getGapSize());
            case TOP:
            case LEFT:
            default:
                return new Point(0,0);
        }
    }
}
