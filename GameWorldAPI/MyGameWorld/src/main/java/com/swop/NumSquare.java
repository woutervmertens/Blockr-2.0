package com.swop;

import java.awt.*;

public class NumSquare {
    private int number;
    private Polygon polygon;

    public NumSquare(int number, int size){
        this.number = number;
        this.polygon = new Polygon(new int[]{1,size-1,size-1,1},
                new int[]{1,1,size-1,size-1},
                4);
    }

    public int getNumber(){return number;}

    public void draw(Point pos,Graphics g){
        polygon.translate(pos.x,pos.y);
        if(number != 0){
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(number),pos.x + 15,pos.y + 15);
        }else{
            g.setColor(Color.green);
        }
        g.drawPolygon(polygon);
        polygon.translate(-pos.x,-pos.y);
    }
}
