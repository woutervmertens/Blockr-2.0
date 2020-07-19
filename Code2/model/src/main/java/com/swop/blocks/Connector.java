package com.swop.blocks;

import java.awt.*;

public class Connector {
    private Point position;
    private int range;

    public Connector (Point position){
        this(position,10);
    }
    public Connector (Point position, int range){
        this.position = position;
        this.range = range;
    }
    public boolean isOnConnector(Point coordinates){
        return getDistance(coordinates,position) <= range;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
    /**
     * @param b Point1
     * @param p Point2
     * @return Returns the distance between the two given points.
     */
    private static int getDistance(Point b, Point p) {
        return (int) Math.sqrt((p.getX() - b.getX()) * (p.getX() - b.getX()) + (p.getY() - b.getY()) * (p.getY() - b.getY()));
    }
}
