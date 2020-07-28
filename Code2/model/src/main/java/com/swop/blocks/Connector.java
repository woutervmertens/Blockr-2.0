package com.swop.blocks;

import java.awt.*;

public class Connector implements Cloneable {
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

    /**
     * @return Returns a clone of the given block.
     */
    public Connector clone() {
        try {
            return (Connector) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isOnConnector(Point coordinates){
        return getDistance(coordinates,position) <= range;
    }

    public Point getPosition() {
        return (Point) position.clone();
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
