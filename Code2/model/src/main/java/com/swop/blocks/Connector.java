package com.swop.blocks;

import java.awt.*;

public class Connector {
    private Point position;
    private int range;
    private BlockModel connectingBlock = null;
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

    public void connect(BlockModel blockModel){
        this.connectingBlock = blockModel;
    }
    public void disconnect(){
        this.connectingBlock = null;
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
