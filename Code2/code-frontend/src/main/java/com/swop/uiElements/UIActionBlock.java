package com.swop.uiElements;

import java.awt.*;

public class UIActionBlock extends UIBlock {

    public UIActionBlock(BlockTypes.stdBlockData data){
        super(data);
    }

    @Override
    public Polygon getPolygon() {
        Polygon pol = new Polygon();
        int step = height / 6;
        pol.addPoint(position.x, position.y);
        //socket
        pol.addPoint(position.x + step * 2, position.y);
        pol.addPoint(position.x + step * 3, position.y + step);
        pol.addPoint(position.x + step * 4, position.y);

        pol.addPoint(position.x + width, position.y);
        pol.addPoint(position.x + width, position.y + height);
        //plug
        pol.addPoint(position.x + step * 4, position.y + height);
        pol.addPoint(position.x + step * 3, position.y + height + step);
        pol.addPoint(position.x + step * 2, position.y + height);

        pol.addPoint(position.x, position.y + height);
        return pol;
    }
}
