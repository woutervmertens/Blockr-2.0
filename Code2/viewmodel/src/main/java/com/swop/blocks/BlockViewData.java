package com.swop.blocks;

import java.awt.*;

/**
 * A data structure to send to the view.
 */
public class BlockViewData {
    private String text;
    private Point textPosition;
    private Color color;
    private Polygon polygon;

    public BlockViewData(String text, Point textPosition, Color color, Polygon polygon) {
        this.text = text;
        this.textPosition = textPosition;
        this.color = color;
        this.polygon = polygon;
    }

    public String getText() {
        return text;
    }

    public Point getTextPosition() {
        return textPosition;
    }

    public Color getColor() {
        return color;
    }

    public Polygon getPolygon() {
        return polygon;
    }
}
