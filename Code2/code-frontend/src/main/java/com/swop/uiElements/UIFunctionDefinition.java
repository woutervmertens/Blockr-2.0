package com.swop.uiElements;

import com.swop.blocks.ActionBlock;
import com.swop.handlers.BlockrGameFacade;

import java.awt.*;


public class UIFunctionDefinition extends UIBlock{
    private int titleWidth;
    private int gapSize;
    private final int pillarWidth = 10;

    public UIFunctionDefinition(int width, int height, Point position, String text, BlockTypes type, Color color, Color highlightColor, BlockrGameFacade facade, int gapSize) {
        super(width, height, position, text, type, color, highlightColor, facade);
        titleWidth = width/3;
        this.gapSize = gapSize;
    }

    public int getGapSize() {
        return gapSize;
    }

    public void setGapSize(int gapSize) {
        this.gapSize = gapSize;
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
