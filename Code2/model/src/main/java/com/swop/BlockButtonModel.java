package com.swop;

import com.swop.blocks.BlockModel;

import java.awt.*;

/**
 * A BlockButton, containing a BlockModel.
 */
public class BlockButtonModel implements Cloneable{
    private Point position;
    private int height;
    private int width;
    private BlockModel blockModel;
    private Color bgColor;

    public BlockButtonModel(Point position, int width, int height, BlockModel blockModel) {
        this.position = position;
        this.height = height;
        this.width = width;
        this.blockModel = blockModel;
        bgColor = Color.lightGray;
        updateBlockPos();
    }

    /**
     * @return Returns a clone of the given block.
     */
    public BlockButtonModel clone() {
        BlockButtonModel cb = new BlockButtonModel((Point) position.clone(),width,height,blockModel.clone());
        return cb;
    }

    private void updateBlockPos(){
        Point newPos = (Point) this.position.clone();
        newPos.translate(15,5);
        blockModel.setPosition(newPos);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
        updateBlockPos();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public BlockModel getBlockModel() {
        return blockModel;
    }

    public void setBlockModel(BlockModel blockModel) {
        this.blockModel = blockModel;
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

    public Polygon getBGPolygon() {
        Polygon pol = new Polygon();
        pol.addPoint(position.x, position.y);

        pol.addPoint(position.x + width, position.y);
        pol.addPoint(position.x + width, position.y + height);

        pol.addPoint(position.x, position.y + height);
        return pol;
    }

    public Color getBGColor() {
        return bgColor;
    }
}
