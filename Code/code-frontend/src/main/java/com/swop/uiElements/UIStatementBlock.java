package com.swop.uiElements;

import com.swop.blocks.Block;
import com.swop.blocks.IfBlock;
import com.swop.blocks.StatementBlock;
import com.swop.blocks.WhileBlock;
import com.swop.windowElements.UIProgramArea;

import java.awt.*;

public class UIStatementBlock extends UIBlock implements VerticallyConnectable {
    private final Block block;
    private int gapSize;
    private int pillarWidth = 10;
    private int conditionWidth;

    public UIStatementBlock(int width, int height, Point position, String text, BlockTypes type, int gapSize) {
        super(width, height, position, text, type);
        switch (type) {
            case IfStatement:
                this.block = new IfBlock();
                break;
            case WhileStatement:
                this.block = new WhileBlock();
                break;
            default:
                throw new IllegalArgumentException("Not a Statement Block !");
        }
        this.gapSize = gapSize;
        color = Color.CYAN;
        highlightColor = Color.getHSBColor(180, 100, 30);
        conditionWidth = width / 2;
    }

    public int getGapSize() {
        return gapSize;
    }

    public void setGapSize(int gapSize) {
        this.gapSize = gapSize;
    }

    public void increaseGapSize(int increase) {this.gapSize += increase;}

    @Override
    public Block getBlock() {
        return this.block;
    }

    @Override
    public UIBlock getParentStatement() {
        return super.parentStatement;
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
        //plug condition
        pol.addPoint(position.x + conditionWidth, position.y + step * 2);
        pol.addPoint(position.x + conditionWidth + step, position.y + step * 3);
        pol.addPoint(position.x + conditionWidth, position.y + step * 4);

        pol.addPoint(position.x + conditionWidth, position.y + height);

        //plug body
        pol.addPoint(position.x + pillarWidth + step * 4, position.y + height);
        pol.addPoint(position.x + pillarWidth + step * 3, position.y + height + step);
        pol.addPoint(position.x + pillarWidth + step * 2, position.y + height);

        //gap
        pol.addPoint(position.x + pillarWidth, position.y + height);
        pol.addPoint(position.x + pillarWidth, position.y + height + gapSize);
        pol.addPoint(position.x + width, position.y + gapSize + height);
        pol.addPoint(position.x + width, position.y + height + pillarWidth + gapSize);

        //plug bottom
        pol.addPoint(position.x + step * 4, position.y + height + pillarWidth + gapSize);
        pol.addPoint(position.x + step * 3, position.y + height + pillarWidth + gapSize + step);
        pol.addPoint(position.x + step * 2, position.y + height + pillarWidth + gapSize);

        pol.addPoint(position.x, position.y + height + pillarWidth + gapSize);
        return pol;
    }

    @Override
    public Point getPlugPosition() {
        return new Point(position.x /*+ step * 3*/, position.y + height + pillarWidth + gapSize + step);
    }

    @Override
    public Point getSocketPosition() {
        return new Point(position.x /*+ step * 3*/, position.y + step);
    }

    public Point getBodyPlugPosition(UIProgramArea pa) {
        if(((StatementBlock)block).getBody().getBlocks().isEmpty())
            return new Point(position.x + pillarWidth, position.y + height + step);

        Block lastBlock = ((StatementBlock)block).getBody().getBlocks().getLast();
        UIBlock b = pa.getUiBlock(lastBlock);
        if(b != null) return b.getPlugPosition();
        else
            throw new NullPointerException("UIStatementBlock: getBodyPlugPosition: no ui block found to match");
    }

    public Point getConditionPlugPosition(UIProgramArea pa) {
        if(((StatementBlock)block).getConditions().isEmpty())
            return new Point(position.x + conditionWidth + step, position.y);

        Block lastBlock = ((StatementBlock)block).getConditions().getLast();
        UIBlock b = pa.getUiBlock(lastBlock);
        if(b != null) return b.getPlugPosition();
        else
            throw new NullPointerException("UIStatementBlock: getBodyPlugPosition: no ui block found to match");
    }

    @Override
    public boolean isPositionOn(int x, int y) {
        return (x > position.x && x < position.x + conditionWidth) && (y > position.y && y < position.y + height);
    }
}
