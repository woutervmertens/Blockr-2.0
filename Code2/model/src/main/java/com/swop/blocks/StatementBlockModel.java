package com.swop.blocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A block that is a statement.
 */
public class StatementBlockModel extends BlockModelWithBody {
    protected List<ConditionBlockModel> conditions = new ArrayList<>();
    private final int conditionWidth;

    /**
     * Creates a block that is a statement with the given position, width and height.
     */
    public StatementBlockModel(StdBlockData data, int conditionWidth) {
        super(data);
        this.conditionWidth = conditionWidth;
        this.color = Color.cyan;
        this.highlightColor = new Color(200,255,255);
        Connectors.put(ConnectorType.TOP,new Connector(this,pointSum(position,ConnectorType.TOP.getOffset(data))));
        Connectors.put(ConnectorType.BOTTOM,new Connector(this,pointSum(position,ConnectorType.BOTTOM.getOffset(data))));
        Connectors.put(ConnectorType.RIGHT,new Connector(this,pointSum(position,ConnectorType.RIGHT.getOffset(data))));
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
        //socket condition
        pol.addPoint(position.x + conditionWidth, position.y + step * 2);
        pol.addPoint(position.x + conditionWidth - step, position.y + step * 3);
        pol.addPoint(position.x + conditionWidth, position.y + step * 4);

        pol.addPoint(position.x + conditionWidth, position.y + height);

        //plug body
        pol.addPoint(position.x + pillarWidth + step * 4, position.y + height);
        pol.addPoint(position.x + pillarWidth + step * 3, position.y + height + step);
        pol.addPoint(position.x + pillarWidth + step * 2, position.y + height);

        //gap
        pol.addPoint(position.x + pillarWidth, position.y + height);
        pol.addPoint(position.x + pillarWidth, position.y + height + gapSize);

        //socket body
        pol.addPoint(position.x + pillarWidth + step * 4, position.y + height + gapSize);
        pol.addPoint(position.x + pillarWidth + step * 3, position.y + height + step + gapSize);
        pol.addPoint(position.x + pillarWidth + step * 2, position.y + height + gapSize);

        //gap bottom
        pol.addPoint(position.x + width, position.y + gapSize + height);
        pol.addPoint(position.x + width, position.y + height + pillarWidth + gapSize);

        //plug bottom
        pol.addPoint(position.x + step * 4, position.y + height + pillarWidth + gapSize);
        pol.addPoint(position.x + step * 3, position.y + height + pillarWidth + gapSize + step);
        pol.addPoint(position.x + step * 2, position.y + height + pillarWidth + gapSize);

        pol.addPoint(position.x, position.y + height + pillarWidth + gapSize);
        return pol;
    }

    public List<ConditionBlockModel> getConditions() {
        return conditions;
    }

    /**
     * Adds the given conditionBlock to the statement and sets the parentBlock of the given block to this statementBlock.
     * @param block The given conditionBlock.
     */
    public void addConditionBlock(ConditionBlockModel block) {
        conditions.add(block);
    }

    /**
     * @return conditionlist is not empty,no NOT at the end and no predicate before the end
     */
    protected boolean checkConditions(){
        if (conditions.isEmpty()) return false;

        int size = conditions.size();
        // Predicate(that isn't NOT) should only be at the last (and has to)
        for (int i = 0; i < size - 1; i++) {
            if (conditions.get(i).isPredicate()) {
                return false;
            }
        }
        if(!conditions.get(size-1).isPredicate()) return false;
        return true;
    }

    /**
     * Removes all the ConditionBlocks behind the given conditionBlock and the given conditionBlock from the statementBlock.
     * @param block The given conditionBlock.
     */
    public void removeConditionBlock(ConditionBlockModel block) {
        assert getConditions().contains(block);

        ConditionBlockModel currentCondition;
        int n = getConditions().size();
        int j = getConditions().indexOf(block);
        for (int i = n - 1; i >= j; i--) {
            currentCondition = getConditions().get(i);
            conditions.remove(currentCondition);
        }
    }

    /**
     * Is the given position on this statement block.
     * This method is overridden bcs statementBlocks should only be clicked on their upper part (conditionWidth).
     */
    @Override
    public boolean isWithin(int x, int y) {
        return (x > getPosition().x && x < getPosition().x + conditionWidth) && (y > getPosition().y && y < getPosition().y + getHeight());
    }

    @Override
    public int getCount() {
        return super.getCount() + getConditions().size();
    }
}