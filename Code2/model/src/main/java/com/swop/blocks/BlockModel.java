package com.swop.blocks;

import java.awt.*;
import java.util.HashMap;

public abstract class BlockModel implements Cloneable {
    protected String text;
    protected final int step;
    protected final int width;
    protected final int height;

    private boolean isFirst = true;
    protected Point position;

    protected Color color, highlightColor;
    protected boolean isHighlight = false;

    protected Connector nextConnector = null;
    protected BlockModel nextBlock = null;

    protected BlockModelType blockModelType = BlockModelType.NULL;

    /**
     * Creates a block with the given position, width and height.
     */
    protected BlockModel(StdBlockData data) {
        this.setPosition(data.getPosition());
        this.width = data.getWidth();
        this.height = data.getHeight();
        this.text = data.getText();
        step = height / 6;
    }

    /**
     * @return Returns a clone of the given block.
     */
    public BlockModel clone() {
        try {
            return (BlockModel) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Point pointSum(Point a, Point b){
        return new Point(a.x + b.x,a.y + b.y);
    }

    public int getStep() {
        return step;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getPosition() {
        return position;
    }

    public Point getTextPosition() {
        return new Point(position.x + 10, position.y + 20);
    }

    public String getText() {
        return text;
    }

    public void setIsFirstFlag(boolean b){ isFirst = b;}
    public boolean isFirst() {return isFirst;}

    public Color getColor() {
        if (isHighlight) return highlightColor;
        return color;
    }

    /**
     * @return the polygon for this type of UIBlock
     */
    public abstract Polygon getPolygon();

    public void setPosition(Point position) {
        if (position == null) throw new IllegalArgumentException();
        this.position = position;
        updateConnectors();
    }

    public void setHighlightState(boolean isHighlight) {
        this.isHighlight = isHighlight;
    }

    public boolean checkLastBlockFlag(){
        return nextBlock == null;
    }

    /**
     * Check whether the pos represented by the given x and y is within this block.
     * @param x The given x
     * @param y The given y
     * @return Boolean
     */
    public boolean isWithin(int x, int y) {
        return (x > getPosition().x
                && x < getPosition().x + getWidth()
                && y > getPosition().y
                && y < getPosition().y + getHeight());
    }

    public BlockModel getNext(){
        return nextBlock;
    }

    public void setNextBlock(BlockModel model) {nextBlock = model;}

    public BlockModelType getBlockModelType(){return blockModelType;}

    public void updateConnectors(){
        if(nextConnector == null) return;
        nextConnector.setPosition(new Point(position.x,position.y + getHeight()));
    }

    public void renewConnectors(){
        if(nextConnector == null) return;
        nextConnector = new Connector(new Point(position.x,position.y + getHeight()));
    }

    public boolean hasConnectedBlock(BlockModel blockModel){
        return nextBlock == blockModel;
    }
}
