package com.swop.uiElements;

import com.swop.worldElements.Direction;

import java.awt.*;

public class UICharacter{
    private Direction direction;
    private Point position;
    private Point offPos;
    public UICharacter(Point position, Direction direction) {
        this.direction = direction;
        this.position = position;
    }

    public void drawCharacter(Graphics g, int size) {
        g.setColor(Color.RED);

        Polygon p = new Polygon();
        switch (direction) {
            case UP:
                p.addPoint(offPos.x + size / 2, offPos.y + 5);           //TopMiddle
                p.addPoint(offPos.x + size - 5, offPos.y + size - 5);    //RightBottom
                p.addPoint(offPos.x + 5,        offPos.y + size - 5);    //LeftBottom
                break;
            case RIGHT:
                p.addPoint(offPos.x + 5,        offPos.y + 5);           //LeftTop
                p.addPoint(offPos.x + size - 5, offPos.y + size / 2);    //RightMiddle
                p.addPoint(offPos.x + 5,        offPos.y + size - 5);    //LeftBottom
                break;
            case DOWN:
                p.addPoint(offPos.x + 5,        offPos.y + 5);           //LeftTop
                p.addPoint(offPos.x + size - 5, offPos.y + 5);           //RightTop
                p.addPoint(offPos.x + size / 2, offPos.y + size - 5);    //BottomMiddle
                break;
            case LEFT:
                p.addPoint(offPos.x + size - 5, offPos.y + 5);           //RightTop
                p.addPoint(offPos.x + size - 5, offPos.y + size - 5);    //RightBottom
                p.addPoint(offPos.x + 5,        offPos.y + size / 2);    //LeftMiddle
                break;
        }
        g.drawPolygon(p);
    }

    public void setOffPos(Point offset) {
        offPos = new Point(offset.x + position.x,offset.y + position.y);
    }
}
