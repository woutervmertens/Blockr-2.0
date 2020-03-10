package UIElements;

import java.awt.*;

public abstract class UIBlock {
    protected int width;
    protected int height = 30;
    protected  boolean isAvailable;
    protected Point position;
    protected Color color, highlightColor;
    protected String text;
    protected BlockTypes type;

    public UIBlock(int width, int height, Point position, String text, BlockTypes type) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.text = text;
        this.type = type;
        isAvailable = false;
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

    public Point getTextPosition(){ return new Point(position.x + 20, position.y + 20);}

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BlockTypes getType() {
        return type;
    }

    public void setType(BlockTypes type) {
        this.type = type;
    }

    public Color getColor(boolean isHiglighted) {
        if(isHiglighted) return highlightColor;
        return color;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Polygon getPolygon()
    {
        return new Polygon();
    }
}

