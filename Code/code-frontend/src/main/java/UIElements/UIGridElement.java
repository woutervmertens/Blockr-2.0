package UIElements;

import java.awt.*;

public abstract class UIGridElement {
    protected Point posInGrid;
    protected Color color;

    public UIGridElement(Point posInGrid) {
        this.posInGrid = posInGrid;
    }

    public Point getPosInGrid() {
        return posInGrid;
    }

    public void setPosInGrid(Point posInGrid) {
        this.posInGrid = posInGrid;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Polygon getPolygon(int size, Point gridPos) {
        return new Polygon();
    }

    /**
     * Returns the position in pixels for the current grid element
     * @param size The size of a grid element
     * @param posOfGrid The position of the grid
     * @return The position of the current grid element
     */
    protected Point getOffset(int size, Point posOfGrid) {
        return new Point(posOfGrid.x + posInGrid.x * size, posOfGrid.y + posInGrid.y * size);
    }

}
