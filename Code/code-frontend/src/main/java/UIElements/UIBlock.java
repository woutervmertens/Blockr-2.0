package UIElements;

import java.awt.*;

public abstract class UIBlock {
    int width;
    int height = 30;
    Point position;

    public UIBlock(int width, int height, Point position) {
        this.width = width;
        this.height = height;
        this.position = position;
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

    public void setPosition(Point position) {
        this.position = position;
    }

    public Polygon getPolygon()
    {
        return new Polygon();
    }
}

