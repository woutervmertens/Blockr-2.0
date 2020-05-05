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

    /**
     * The number value of this square.
     * @return int
     */
    public int getNumber(){return number;}

    /**
     * The polygon of this square.
     * @return Polygon
     */
    Polygon getPolygon() {
        return polygon;
    }

    /**
     * Draw this square.
     * @param pos The position of the square on the screen.
     * @param g The Graphics object.
     */
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
