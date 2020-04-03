package com.swop.uiElements;

import com.swop.blocks.Block;
import com.swop.blocks.IfBlock;
import com.swop.blocks.WhileBlock;
import com.swop.worldElements.GameWorld;

import java.awt.*;

public class UIStatementBlock extends UIBlock {
    private Block correspondingBlock = null;
    private int gapSize;
    private int pillarWidth = 10;
    private int conditionWidth;

    public UIStatementBlock(int width, int height, Point position, String text, BlockTypes type, int gapSize) {
        super(width, height, position, text);
        this.type = type;
        this.gapSize = gapSize;
        color = Color.CYAN;
        highlightColor = Color.getHSBColor(180, 100, 30);
        conditionWidth = width / 2;
    }

    public int getGapSize() {
        return gapSize;
    }

    private void setGapSize(int gapSize) {
        this.gapSize = gapSize;
    }

    public void increaseGapSize(int increase) {this.gapSize += increase;}

    public  void decreaseGapSize(int increase) {this.gapSize -= increase;}

    @Override
    public Block getCorrespondingBlock() {
        return this.correspondingBlock;
    }

    @Override
    public void makeNewCorrespondingBlockIn(GameWorld gameWorld) {
        switch (type) {
            case IfStatement:
                this.correspondingBlock = new IfBlock(getPosition(), gameWorld, getWidth(), getHeight());
                break;
            case WhileStatement:
                this.correspondingBlock = new WhileBlock(getPosition(), gameWorld, getWidth(), getHeight());
                break;
            default:
                throw new IllegalArgumentException("Not a Statement Block !");
        }
    }

    @Override
    public int getHeight() {
        return height + gapSize + pillarWidth;
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
