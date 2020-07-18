package com.swop.blocks;

import java.awt.*;

public class Connector {
    private Point position;
    private int range;
    private Connector connectedTo = null;
    private BlockModel blockModel;
    public Connector (BlockModel blockModel,Point position){
        this(blockModel,position,10);
    }
    public Connector (BlockModel blockModel, Point position, int range){
        this.position = position;
        this.range = range;
        this.blockModel = blockModel;
    }
    public boolean isOnConnector(Point coordinates){
        return getDistance(coordinates,position) <= range;
    }

    public void connect(Connector connectTo){
        this.connectedTo = connectTo;
    }
    public void disconnect(){
        this.connectedTo = null;
    }
    public BlockModel getConnectingBlock() {
        assert connectedTo != null;
        return connectedTo.getBlockModel();
    }
    public BlockModel getBlockModel() { return blockModel;}
    /**
     * @param b Point1
     * @param p Point2
     * @return Returns the distance between the two given points.
     */
    private static int getDistance(Point b, Point p) {
        return (int) Math.sqrt((p.getX() - b.getX()) * (p.getX() - b.getX()) + (p.getY() - b.getY()) * (p.getY() - b.getY()));
    }
}
