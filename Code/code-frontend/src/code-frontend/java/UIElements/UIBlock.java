package UIElements;

import blocks.Block;

import java.awt.*;
import java.util.ArrayList;

public abstract class UIBlock {
    protected UIBlock previous;
    protected UIBlock next;
    protected int width;
    protected int height;  // default 30 ?
    protected int step;  // steps in the plugs and sockets
    protected boolean isAvailable;
    protected Point position;
    protected Color color, highlightColor;
    protected final String text;
    protected BlockTypes type;

    public UIBlock(int width, int height, Point position, String text, BlockTypes type) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.text = text;
        this.type = type;
        isAvailable = false;
        step = height / 6;
    }

    /**
     * @return A reference to the attached block in Backend
     */
    public abstract Block getBlock();

    public UIBlock getPrevious() {
        return previous;
    }

    public void setPrevious(UIBlock previous) {
        this.previous = previous;
    }

    public UIBlock getNext() {
        return next;
    }

    public void setNext(UIBlock next) {
        this.next = next;
    }

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

    public Point getPosition() {
        return position;
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

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public BlockTypes getType() {
        return type;
    }

    public void setType(BlockTypes type) {
        this.type = type;
    }

    public Color getColor(boolean isHiglighted) {
        if (isHiglighted) return highlightColor;
        return color;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public abstract Polygon getPolygon();

    public abstract Point getSocketPosition();

    public abstract Point getPlugPosition();


}

