package com.swop.blocks;

import java.awt.*;

public class Connector {
    private Point position;
    private int range;
    private ConnectorType type;

    public Connector (Point position){
        this(position,10, ConnectorType.NEXT);
    }
    public Connector (Point position, ConnectorType type){
        this(position,10, type);
    }
    public Connector (Point position, int range, ConnectorType type){
        this.position = position;
        this.range = range;
        this.type = type;
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

    public ConnectorType getType(){
        return type;
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
