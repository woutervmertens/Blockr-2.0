package com.swop.uiElements;

import com.swop.blocks.ActionBlock;
import com.swop.blocks.Block;

import java.awt.*;

public class UIActionBlock extends UIBlock {
    private Block correspondingBlock = null;

    public UIActionBlock(int width, int height, Point position, String text, BlockTypes type, Color color, Color highlightColor) {
        super(width, height, position, text, type, color, highlightColor);
    }

    @Override
    public Block getCorrespondingBlock() {
        return this.correspondingBlock;
    }

    @Override
    public void makeNewCorrespondingBlock() {
        if (type.getType() == BlockType.ActionType) {
            this.correspondingBlock = new ActionBlock(getPosition(), getWidth(), getHeight(), type.getAction());
        } else {
            throw new IllegalArgumentException("Not an Action Block !");
        }
    }

    @Override
    public Polygon getPolygon() {
        Polygon pol = new Polygon();
        int step = height / 6;
        pol.addPoint(position.x, position.y);
        //socket
        pol.addPoint(position.x + step * 2, position.y);
        pol.addPoint(position.x + step * 3, position.y + step);
        pol.addPoint(position.x + step * 4, position.y);

        pol.addPoint(position.x + width, position.y);
        pol.addPoint(position.x + width, position.y + height);
        //plug
        pol.addPoint(position.x + step * 4, position.y + height);
        pol.addPoint(position.x + step * 3, position.y + height + step);
        pol.addPoint(position.x + step * 2, position.y + height);

        pol.addPoint(position.x, position.y + height);
        return pol;
    }
}
