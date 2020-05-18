package com.swop.uiElements;

import com.swop.blocks.ActionBlock;

import java.awt.*;

public class UIFunctionDefinition extends UIBlock{
    public UIFunctionDefinition(int width, int height, Point position, String text, BlockTypes type, Color color, Color highlightColor) {
        super(width, height, position, text, type, color, highlightColor);
    }

    @Override
    public void makeNewCorrespondingBlock() {
        if (getType().getType() == BlockType.FunctionDefinition) {
            setCorrespondingBlock(new ActionBlock(getPosition(), getWidth(), getHeight(), getType().getAction())); //TODO: replace
        } else {
            throw new IllegalArgumentException("Not a Function Definition Block !");
        }
    }

    @Override
    public Polygon getPolygon() {
        return null;
    }
}
