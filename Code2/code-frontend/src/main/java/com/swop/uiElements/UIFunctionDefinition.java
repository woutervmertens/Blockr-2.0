package com.swop.uiElements;

import java.awt.*;

public class UIFunctionDefinition extends UIBlock{
    public UIFunctionDefinition(int width, int height, Point position, String text, BlockTypes type, Color color, Color highlightColor) {
        super(width, height, position, text, type, color, highlightColor);
    }

    @Override
    public void makeNewCorrespondingBlock() {

    }

    @Override
    public Polygon getPolygon() {
        return null;
    }
}
