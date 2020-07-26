package com.swop.blocks;

import java.awt.*;

public enum ConnectorType {
    NEXT,
    BODY,
    CONDITION;

    public Point getOffset(StdBlockData data){
        switch (this){
            case NEXT:
                return new Point(0,data.getHeight());
            case CONDITION:
                return new Point(data.getTitleWidth(),0);
            case BODY:
                return new Point(data.getPillarWidth(),data.getTitleHeight());
            default:
                return new Point(0,0);
        }
    }
}
