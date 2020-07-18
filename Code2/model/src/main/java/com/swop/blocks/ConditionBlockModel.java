package com.swop.blocks;

import com.swop.Predicate;

import java.awt.*;

/**
 * A block that is a condition.
 */
public class ConditionBlockModel extends BlockModel {
    private final boolean isPredicate;
    private final Predicate predicate;

    /**
     * create a condition BLock
     * @param isPredicate Variable registering whether the to make block is a predicate (true) otherwise NOT (false).
     * @param predicate predicate of the block
     */
    public ConditionBlockModel(StdBlockData data, boolean isPredicate, Predicate predicate) {
        super(data);
        this.isPredicate = isPredicate;
        this.predicate = predicate;
        this.color = Color.ORANGE;
        this.highlightColor = new Color(255,255,145);
        Connectors.put(ConnectorType.LEFT,new Connector(this,pointSum(position,ConnectorType.LEFT.getOffset(data))));
        if(!isPredicate)Connectors.put(ConnectorType.RIGHT,new Connector(this,pointSum(position,ConnectorType.RIGHT.getOffset(data))));
    }

    public boolean isPredicate() {
        return isPredicate;
    }
    public Predicate getPredicate() { return predicate;}

    @Override
    public Polygon getPolygon() {
        Polygon pol = new Polygon();
        int step = height / 6;
        pol.addPoint(position.x, position.y);
        pol.addPoint(position.x + width, position.y);
        if (!isPredicate) {
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
