package com.swop.uiElements;

import com.swop.blocks.Block;
import com.swop.blocks.IfBlock;
import com.swop.blocks.WhileBlock;

import java.awt.*;

public class UIStatementBlock extends UIBlock {

    private int gapSize;
    private final int pillarWidth = 10;
    private final int conditionWidth;

    public UIStatementBlock(int width, int height, Point position, String text, BlockTypes type, Color color, Color highlightColor, int gapSize) {
        super(width, height, position, text, type, color, highlightColor);
        this.gapSize = gapSize;
        conditionWidth = width / 2;
    }

    public int getGapSize() {
        return gapSize;
    }

    public void setGapSize(int gapSize) {
        this.gapSize = gapSize;
    }

    @Override
    public void makeNewCorrespondingBlock() {
        switch (getType().getType()) {
            case IfStatement:
                setCorrespondingBlock(new IfBlock(getPosition(), getWidth(), getHeight()));
                break;
            case WhileStatement:
                setCorrespondingBlock(new WhileBlock(getPosition(), getWidth(), getHeight()));
                break;
            default:
                throw new IllegalArgumentException("Not a Statement Block !");
        }
    }

    @Override
    public int getHeight() {
        return height + getGapSize() + pillarWidth;
    }

    @Override
    public Polygon getPolygon() {
        Polygon pol = new Polygon();
        pol.addPoint(position.x, position.y);
        //socket top
        pol.addPoint(position.x + step * 2, position.y);
        pol.addPoint(position.x + step * 3, position.y + step);
        pol.addPoint(position.x + step * 4, position.y);

        pol.addPoint(position.x + conditionWidth, position.y);
        //socket condition
        pol.addPoint(position.x + conditionWidth, position.y + step * 2);
        pol.addPoint(position.x + conditionWidth - step, position.y + step * 3);
        pol.addPoint(position.x + conditionWidth, position.y + step * 4);

        pol.addPoint(position.x + conditionWidth, position.y + height);

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
        pol.addPoint(position.x + width, position.y + gapSize + height);
        pol.addPoint(position.x + width, position.y + height + pillarWidth + gapSize);

        //plug bottom
        pol.addPoint(position.x + step * 4, position.y + height + pillarWidth + gapSize);
        pol.addPoint(position.x + step * 3, position.y + height + pillarWidth + gapSize + step);
        pol.addPoint(position.x + step * 2, position.y + height + pillarWidth + gapSize);

        pol.addPoint(position.x, position.y + height + pillarWidth + gapSize);
        return pol;
    }
}
