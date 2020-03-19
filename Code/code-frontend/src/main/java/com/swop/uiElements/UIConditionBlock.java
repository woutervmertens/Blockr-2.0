package com.swop.uiElements;

import com.swop.blocks.Block;
import com.swop.blocks.NotBlock;
import com.swop.blocks.WallInFrontBlock;

import java.awt.*;

public class UIConditionBlock extends UIBlock implements HorizontallyConnectable {
    private final Block block;

    public UIConditionBlock(int width, int height, Point position, String text, BlockTypes type) {
        super(width, height, position, text, type);
        switch (type) {
            case WallInFrontCondition:
                this.block = new WallInFrontBlock();
                break;
            case NotCondition:
                this.block = new NotBlock();
                break;
            default:
                throw new IllegalArgumentException("Not a Condition com.swop.blocks.Block !");
        }
        color = Color.orange;
        highlightColor = Color.getHSBColor(45, 65, 100); //light orange
    }

    @Override
    public Block getBlock() {
        return this.block;
    }

    @Override
    public Polygon getPolygon() {
        Polygon pol = new Polygon();
        int step = height / 6;
        pol.addPoint(position.x, position.y);
        pol.addPoint(position.x + width, position.y);
        //plug
        pol.addPoint(position.x + width, position.y + step * 2);
        pol.addPoint(position.x + width + step, position.y + step * 3);
        pol.addPoint(position.x + width, position.y + step * 4);

        pol.addPoint(position.x + width, position.y + height);
        pol.addPoint(position.x, position.y + height);
        //socket
        pol.addPoint(position.x, position.y + step * 2);
        pol.addPoint(position.x + step, position.y + step * 3);
        pol.addPoint(position.x, position.y + step * 4);
        return pol;
    }

    @Override
    public Point getPlugPosition() {
        return new Point(position.x + width + step, position.y + step * 3);
    }

    @Override
    public Point getSocketPosition() {
        return new Point(position.x + step, position.y + step * 3);
    }


}
