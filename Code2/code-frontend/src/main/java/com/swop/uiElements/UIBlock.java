package com.swop.uiElements;

import com.swop.handlers.BlockrGameFacade;

import java.awt.*;

public abstract class UIBlock {
    protected final String text;
    protected int width;
    protected int height;  // default 30 ?
    protected int step;  // steps in the plugs and sockets
    protected Point position;
    protected Color color, highlightColor;
    private BlockTypes type;
    protected BlockrGameFacade blockrGameFacade;
    protected boolean isHighlight;

    public UIBlock(int width, int height, Point position, String text, BlockTypes type, Color color, Color highlightColor, BlockrGameFacade facade) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.text = text;
        this.type = type;
        this.color = color;
        this.highlightColor = highlightColor;
        this.blockrGameFacade = facade;
        step = height / 6;
    }

    public void makeNewCorrespondingBlock() {blockrGameFacade.makeNewCorrespondingBlock(this);}

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
        if (blockrGameFacade.getCorrespondingBlockFor(this) != null) {
            blockrGameFacade.getCorrespondingBlockFor(this).setPosition(position);
        }
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
}
