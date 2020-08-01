package com.swop.blocks;

import com.swop.Action;

import java.awt.*;

/**
 * A block to perform an action.
 */
public class ActionBlockModel extends BlockModel {
    private final Action action;

    /**
     * Creates an actionBlock with the given position, width, height and action.
     * @param action The action that the actionBlock can perform on the gameworld.
     */
    public ActionBlockModel(StdBlockData data, Action action) {
        super(data);
        this.action = action;
        this.color = Color.RED;
        this.highlightColor = new Color(255,140,140);
        nextConnector = new Connector(pointSum(position,ConnectorType.NEXT.getOffset(data)));
        blockModelType = BlockModelType.ACTION;
    }

    @Override
    public BlockModel clone() {
        ActionBlockModel ca = new ActionBlockModel(new StdBlockData((Point) getPosition().clone(),getWidth(),getHeight(),getText()),action);
        ca.setHighlightState(isHighlight);
        ca.setNextBlock(getNext());
        ca.setIsFirstFlag(isFirst());
        return ca;
    }

    protected Action getAction()
    {
        return action;
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
