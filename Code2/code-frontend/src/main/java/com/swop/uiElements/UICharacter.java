package com.swop.uiElements;

import com.swop.worldElements.Direction;
import com.swop.worldElements.Square;

import java.awt.*;

public class UICharacter{
    private Direction direction;
    private Point position;
    public UICharacter(Point position, Direction direction) {
        this.direction = direction;
        this.position = position;
    }

    public void drawCharacter(Graphics g, int size) {
        g.setColor(Color.RED);

        Polygon p = new Polygon();
        switch (direction) {
            case UP:
                p.addPoint(position.x + size / 2, position.y + 5);           //TopMiddle
                p.addPoint(position.x + size - 5, position.y + size - 5);    //RightBottom
                p.addPoint(position.x + 5, position.y + size - 5);    //LeftBottom
                break;
            case RIGHT:
                p.addPoint(position.x + 5, position.y + 5);           //LeftTop
                p.addPoint(position.x + size - 5, position.y + size / 2);    //RightMiddle
                p.addPoint(position.x + 5, position.y + size - 5);    //LeftBottom
                break;
            case DOWN:
                p.addPoint(position.x + 5, position.y + 5);           //LeftTop
                p.addPoint(position.x + size - 5, position.y + 5);           //RightTop
                p.addPoint(position.x + size / 2, position.y + size - 5);    //BottomMiddle
                break;
            case LEFT:
                p.addPoint(position.x + size - 5, position.y + 5);           //RightTop
                p.addPoint(position.x + size - 5, position.y + size - 5);    //RightBottom
                p.addPoint(position.x + 5, position.y + size / 2);    //LeftMiddle
                break;
        }
        g.drawPolygon(p);
    }
}
