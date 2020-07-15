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
        Connectors.put(new Connector(pointSum(position,ConnectorType.TOP.getOffset(data))),ConnectorType.TOP);
        Connectors.put(new Connector(pointSum(position,ConnectorType.BOTTOM.getOffset(data))),ConnectorType.BOTTOM);
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
