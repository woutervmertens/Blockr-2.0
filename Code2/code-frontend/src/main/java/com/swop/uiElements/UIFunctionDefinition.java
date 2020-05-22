package com.swop.uiElements;

import java.awt.*;


public class UIFunctionDefinition extends UIBlockWithBody{
    private int titleWidth;

    public UIFunctionDefinition(int width, int height, Point position, String text, BlockTypes type, Color color, Color highlightColor, int gapSize) {
        super(width, height, position, text, type, color, highlightColor, gapSize);
        titleWidth = width/3;
    }

    @Override
    public Polygon getPolygon() {
        Polygon pol = new Polygon();
        pol.addPoint(position.x, position.y);
        pol.addPoint(position.x + titleWidth, position.y);

        pol.addPoint(position.x + titleWidth, position.y + height);

        //plug body
        pol.addPoint(position.x + pillarWidth + step * 4, position.y + height);
        pol.addPoint(position.x + pillarWidth + step * 3, position.y + height + step);
        pol.addPoint(position.x + pillarWidth + step * 2, position.y + height);

        //gap
        pol.addPoint(position.x + pillarWidth, position.y + height);
        pol.addPoint(position.x + pillarWidth, position.y + height + gapSize);

        //socket body
        pol.addPoint(position.x + pillarWidth + step * 4, position.y + height + gapSize);
        pol.addPoint(position.x + pillarWidth + step * 3, position.y + height + step + gapSize);
        pol.addPoint(position.x + pillarWidth + step * 2, position.y + height + gapSize);

        //gap bottom
        pol.addPoint(position.x + titleWidth, position.y + gapSize + height);
        pol.addPoint(position.x + titleWidth, position.y + height + pillarWidth + gapSize);

        pol.addPoint(position.x, position.y + height + pillarWidth + gapSize);
        return pol;
    }
}
