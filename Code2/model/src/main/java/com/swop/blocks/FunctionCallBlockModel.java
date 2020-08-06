package com.swop.blocks;

import java.awt.*;

/**
 * A block that calls a function.
 */
public class FunctionCallBlockModel extends BlockModel{
    private final FunctionDefinitionBlockModel definitionBlock;

    /**
     * Creates a functionCallBlock with the given position, width, height and definitionBlock.
     *
     * @param data standard data container for the Block
     * @param definitionBlock the given functionDefinitionBlock that it calls.
     */
    public FunctionCallBlockModel(StdBlockData data, FunctionDefinitionBlockModel definitionBlock) {
        super(data);
        this.definitionBlock = definitionBlock;
        this.color = Color.GRAY;
        this.highlightColor = Color.white;
        //definitionBlock.addCall(this);
        blockModelType = BlockModelType.FUNCCALL;
        nextConnector = new Connector(pointSum(position,ConnectorType.NEXT.getOffset(data)));
    }

    /**
     * Clones the object.
     * @return A copy of this FunctionCallBlockModel object.
     */
    @Override
    public BlockModel clone() {
        FunctionCallBlockModel cf = new FunctionCallBlockModel(new StdBlockData((Point) getPosition().clone(),getWidth(),getHeight(),getText()),definitionBlock);
        cf.setHighlightState(isHighlight);
        cf.setNextBlock(getNext());
        cf.setIsFirstFlag(isFirst());
        return cf;
    }

    public FunctionDefinitionBlockModel getDefinitionBlock() {
        return definitionBlock;
    }

    /**
     * Creates and return the polygon for the View to display.
     * @return a Polygon object
     */
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
