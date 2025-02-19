package com.swop.blocks;

import java.awt.*;
import java.util.HashMap;

/**
 * The Block base.
 */
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
     *
     * @param data standard data container for the Block
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
    public abstract BlockModel clone();

    protected Point pointSum(Point a, Point b){
        return new Point(a.x + b.x,a.y + b.y);
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

    /**
     * Sets the next block as the block given and handles the highlight.
     * @param model the BlockModel to inject
     */
    public void setNextBlock(BlockModel model) {
        nextBlock = model;
        if(nextBlock != null) {
            nextBlock.setIsFirstFlag(false);
            nextBlock.setHighlightState(false);
        }
    }

    public BlockModelType getBlockModelType(){return blockModelType;}

    /**
     * Updates the positions of the connectors
     */
    public void updateConnectors(){
        if(nextConnector == null) return;
        nextConnector.setPosition(new Point(position.x,position.y + getHeight()));
    }

    /**
     * Exchanges the connectors with new ones.
     */
    public void renewConnectors(){
        if(nextConnector == null) return;
        nextConnector = new Connector(new Point(position.x,position.y + getHeight()));
    }

    /**
     * Is the given block connected to this block by one of its connectors?
     * @param blockModel the block to check for
     * @return the boolean answer
     */
    public boolean hasConnectedBlock(BlockModel blockModel){
        return nextBlock == blockModel;
    }
}
