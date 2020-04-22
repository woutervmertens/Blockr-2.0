package com.swop.uiElements;

import com.swop.Action;
import com.swop.GameWorld;
import com.swop.blocks.ActionBlock;
import com.swop.blocks.Block;

import java.awt.*;

public class UIActionBlock extends UIBlock {
    private Block correspondingBlock = null;

    public UIActionBlock(int width, int height, Point position, String text, BlockTypes type) {
        super(width, height, position, text);
        this.type = type;
        color = Color.red;
        highlightColor = Color.getHSBColor(0, 80, 100); //light red
    }

    @Override
    public Block getCorrespondingBlock() {
        return this.correspondingBlock;
    }

    @Override
    public void makeNewCorrespondingBlock() {
        switch (type.getType()) {
            case ActionType:
                this.correspondingBlock = new ActionBlock(getPosition(), getWidth(), getHeight(), type.getAction());
                break;
            default:
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
