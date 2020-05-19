package com.swop.uiElements;

import com.swop.blocks.ConditionBlock;
import com.swop.handlers.BlockrGameFacade;

import java.awt.*;

public class UIConditionBlock extends UIBlock {

    public UIConditionBlock(int width, int height, Point position, String text, BlockTypes type, Color color, Color highlightColor, BlockrGameFacade facade) {
        super(width, height, position, text, type, color, highlightColor, facade);
    }

    @Override
    public void makeNewCorrespondingBlock() {
        switch (getType().getType()) {
            case Predicate:
                setCorrespondingBlock(new ConditionBlock(getPosition(), true, getWidth(), getHeight(), getType().getPredicate()));
                break;
            case NotCondition:
                setCorrespondingBlock(new ConditionBlock(getPosition(), false, getWidth(), getHeight(), null));
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
        if (getType().getType() != BlockType.Predicate) {
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
