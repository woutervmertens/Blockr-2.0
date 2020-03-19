package com.swop.uiElements;

import java.awt.*;

public class UICharacter extends UIGridElement {


    Directions direction; //replace with the one from backend

    public UICharacter(Point posInGrid, Directions direction) {
        super(posInGrid);
        color = Color.red;
        this.direction = direction;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    @Override
    public Polygon getPolygon(int size, Point gridPos) {
        Point offset = getOffset(size, gridPos);
        Polygon p = new Polygon();
        switch (direction) {
            case UP:
                p.addPoint(offset.x + size / 2, offset.y + 5);            //TopMiddle
                p.addPoint(offset.x + size - 5, offset.y + size - 5);    //RightBottom
                p.addPoint(offset.x + 5, offset.y + size - 5);          //LeftBottom
                break;
            case RIGHT:
                p.addPoint(offset.x + 5, offset.y + 5);                  //LeftTop
                p.addPoint(offset.x + size - 5, offset.y + size / 2);      //RightMiddle
                p.addPoint(offset.x + 5, offset.y + size - 5);          //LeftBottom
                break;
            case DOWN:
                p.addPoint(offset.x + 5, offset.y + 5);                  //LeftTop
                p.addPoint(offset.x + size - 5, offset.y + 5);           //RightTop
                p.addPoint(offset.x + size / 2, offset.y + size - 5);     //BottomMiddle
                break;
            case LEFT:
                p.addPoint(offset.x + size - 5, offset.y + 5);           //RightTop
                p.addPoint(offset.x + size - 5, offset.y + size - 5);    //RightBottom
                p.addPoint(offset.x + 5, offset.y + size / 2);            //LeftMiddle
                break;
        }
        return p;
    }
}

