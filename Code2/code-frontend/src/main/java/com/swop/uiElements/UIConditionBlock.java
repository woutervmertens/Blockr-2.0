package com.swop.uiElements;

import com.swop.blocks.Block;
import com.swop.blocks.ConditionBlock;

import java.awt.*;

public class UIConditionBlock extends UIBlock {
    private Block correspondingBlock;

    public UIConditionBlock(int width, int height, Point position, String text, BlockTypes type) {
        super(width, height, position, text);
        this.type = type;
        color = Color.orange;
        highlightColor = Color.getHSBColor(45, 65, 100); //light orange
    }

    @Override
    public Block getCorrespondingBlock() {
        return this.correspondingBlock;
    }

    @Override
    public void makeNewCorrespondingBlock() {
        switch (type.getType()) {
            case Predicate:
                this.correspondingBlock = new ConditionBlock(getPosition(), true, getWidth(), getHeight());
                break;
            case NotCondition:
                this.correspondingBlock = new ConditionBlock(getPosition(), false, getWidth(), getHeight());
                break;
            default:
                throw new IllegalArgumentException("Not a Condition Block !");
        }
    }

    @Override
    public Polygon getPolygon() {
        Polygon pol = new Polygon();
        int step = height / 6;
        pol.addPoint(position.x, position.y);
        pol.addPoint(position.x + width, position.y);
        if (type.getType() != BlockType.Predicate) {
            //socket
            pol.addPoint(position.x + width, position.y + step * 2);
            pol.addPoint(position.x + width - step, position.y + step * 3);
            pol.addPoint(position.x + width, position.y + step * 4);
        }

        pol.addPoint(position.x + width, position.y + height);
        pol.addPoint(position.x, position.y + height);

        //plug
        pol.addPoint(position.x, position.y + step * 2);
        pol.addPoint(position.x - step, position.y + step * 3);
        pol.addPoint(position.x, position.y + step * 4);

        return pol;
    }
}
