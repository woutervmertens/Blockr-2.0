package com.swop.blocks;

import java.awt.*;

/**
 * The types of connectors.
 */
public enum ConnectorType {
    NEXT,
    BODY,
    CONDITION,
    NEXTCONDITION;

    public Point getOffset(StdBlockData data){
        switch (this){
            case NEXT:
                return new Point(0,data.getHeight());
            case CONDITION:
                return new Point(data.getTitleWidth(),0);
            case NEXTCONDITION:
                return new Point(data.getWidth(),0);
            case BODY:
                return new Point(data.getPillarWidth(),data.getTitleHeight());
            default:
                return new Point(0,0);
        }
    }
}
