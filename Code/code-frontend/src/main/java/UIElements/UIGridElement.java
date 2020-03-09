package UIElements;

import java.awt.*;

public abstract class UIGridElement {
    protected Point gridPosition;
    protected Color color;

    public UIGridElement(Point gridPosition) {
        this.gridPosition = gridPosition;
    }

    public Point getGridPosition() {
        return gridPosition;
    }

    public void setGridPosition(Point gridPosition) {
        this.gridPosition = gridPosition;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Polygon getPolygon(int size)
    {
        return new Polygon();
    }

}
