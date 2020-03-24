package com.swop.uiElements;

import com.swop.worldElements.Square;

import java.awt.*;

public class UISquare {
    private Point position;
    private Point offPos;
    private Square type;

    public UISquare(Point position, Square type) {
        this.position = position;
        this.type = type;
    }

    public void draw(Graphics g, int size){
        switch (type) {
            case WALL:
                drawWall(g,size);
                break;
            case FLAG:
                drawFlag(g,size);
                break;
            default:
                break;
        }
    }

    public void setOffPos(Point offset){
        offPos = new Point(offset.x + position.x,offset.y + position.y);
    }

    public void drawWall(Graphics g, int size){
        g.setColor(Color.DARK_GRAY);

        Polygon p = new Polygon();
        p.addPoint(offPos.x + 5,        offPos.y + 5);
        p.addPoint(offPos.x + size - 5, offPos.y + 5);
        p.addPoint(offPos.x + size - 5, offPos.y + size - 5);
        p.addPoint(offPos.x + 5,        offPos.y + size - 5);

        g.drawPolygon(p);
    }
    public void drawFlag(Graphics g, int size){
        g.setColor(new Color(0,150,50));

        Polygon p = new Polygon();
        p.addPoint(offPos.x + 5,              offPos.y + 5);
        p.addPoint(offPos.x + size - 5,       offPos.y + 5);
        p.addPoint(offPos.x + size - 5,       offPos.y + size - 5);
        p.addPoint(offPos.x + size/2 + 1,     offPos.y + size - 5);
        p.addPoint(offPos.x + size/2 + 1,     offPos.y + 2*size/3);
        p.addPoint(offPos.x + 2*(size/3) + 1, offPos.y + size/2);
        p.addPoint(offPos.x + size/2 + 1,     offPos.y + size/3);
        p.addPoint(offPos.x + size/2 - 1,     offPos.y + size/3);
        p.addPoint(offPos.x + size/2 - 1,     offPos.y + size - 5);
        p.addPoint(offPos.x + 5,              offPos.y + size - 5);

        g.drawPolygon(p);
    }
}
