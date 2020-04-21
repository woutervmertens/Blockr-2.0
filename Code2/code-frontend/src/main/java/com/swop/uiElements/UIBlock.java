package com.swop.uiElements;

import com.swop.GameWorld;
import com.swop.blocks.Block;

import java.awt.*;

public abstract class UIBlock {
    protected final String text;
    protected int width;
    protected int height;  // default 30 ?
    protected int step;  // steps in the plugs and sockets
    protected Point position;
    protected Color color, highlightColor;
    protected BlockTypes type;
    protected boolean isHighlight;
    /**
     * CorrespondingBlock is only made when a block is released in PA
     */
    protected Block correspondingBlock;

    public UIBlock(int width, int height, Point position, String text) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.text = text;
        step = height / 6;
    }

    public int getStep() {
        return step;
    }

    public Block getCorrespondingBlock() {
        return this.correspondingBlock;
    }

    public abstract void makeNewCorrespondingBlock();

    public void setCorrespondingBlock(Block correspondingBlock) {
        this.correspondingBlock = correspondingBlock;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
        if (getCorrespondingBlock() != null) {
            getCorrespondingBlock().setPosition(position);
        }
    }

    /**
     * Check whether the given position is on this block.
     */
    public boolean isPositionOn(int x, int y) {
        return (x > position.x && x < position.x + width) && (y > position.y && y < position.y + height);
    }

    public Point getTextPosition() {
        return new Point(position.x + 10, position.y + 20);
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        if (isHighlight()) return highlightColor;
        return color;
    }

    public BlockTypes getType() {
        return this.type;
    }

    /**
     * @return the polygon for this type of UIBlock
     */
    public abstract Polygon getPolygon();

    // TODO: getSocket/PlugPosition here or in Backend only ??

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isHighlight() {
        return this.isHighlight;
    }

    public void setHighlightStateOn(boolean isHighlight) {
        this.isHighlight = isHighlight;
    }

    public void terminate() {
        if (correspondingBlock != null) {
            this.getCorrespondingBlock().terminate();
            this.setCorrespondingBlock(null);
        }
        this.setPosition(null);
    }
}
