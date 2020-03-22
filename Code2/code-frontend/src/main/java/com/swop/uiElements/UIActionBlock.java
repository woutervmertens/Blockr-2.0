package com.swop.uiElements;

import com.swop.blocks.Block;
import com.swop.blocks.MoveBlock;
import com.swop.blocks.TurnBlock;
import com.swop.worldElements.Direction;

import java.awt.*;

public class UIActionBlock extends UIBlock {
    private final Block correspondingBlock;

    public UIActionBlock(int width, int height, Point position, String text, BlockTypes type) {
        super(width, height, position, text);
        this.type = type;
        switch (type) {
            case MoveForward:
                this.correspondingBlock = new MoveBlock();
                break;
            case TurnLeft:
                this.correspondingBlock = new TurnBlock(Direction.LEFT);
                break;
            case TurnRight:
                this.correspondingBlock = new TurnBlock(Direction.RIGHT);
                break;
            default:
                throw new IllegalArgumentException("Not an Action Block !");
        }
        color = Color.red;
        highlightColor = Color.getHSBColor(0, 80, 100); //light red
    }

    @Override
    public Block getCorrespondingBlock() {
        return this.correspondingBlock;
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
