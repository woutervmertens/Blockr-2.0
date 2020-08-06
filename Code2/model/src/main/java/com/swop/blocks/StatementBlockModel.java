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
    protected Connector conditionConnector;
    protected ConditionBlockModel firstCondition;
    private Point conditionOffset;
    /**
     * Creates a block that is a statement with the given position, width and height.
     *
     * @param data standard data container for the Block
     * @param conditionWidth the width of a condition Block
     */
    public StatementBlockModel(StdBlockData data, int conditionWidth) {
        super(data);
        this.conditionWidth = conditionWidth;
        this.color = Color.cyan;
        this.highlightColor = new Color(200,255,255);
        conditionOffset = ConnectorType.CONDITION.getOffset(data);
        conditionConnector = new Connector(pointSum(position,conditionOffset),ConnectorType.CONDITION);
        Point gapOffset = new Point(0,getHeight());
        nextConnector = new Connector(pointSum(position,gapOffset));
    }

    @Override
    public BlockModel clone() {
        return null;
    }

    /**
     * Creates and return the polygon for the View to display.
     * @return a Polygon object
     */
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
        pol.addPoint(position.x + pillarWidth, position.y + height + getGapSize());

        //socket body
        pol.addPoint(position.x + pillarWidth + step * 4, position.y + height + getGapSize());
        pol.addPoint(position.x + pillarWidth + step * 3, position.y + height + step + getGapSize());
        pol.addPoint(position.x + pillarWidth + step * 2, position.y + height + getGapSize());

        //gap bottom
        pol.addPoint(position.x + width, position.y + getGapSize() + height);
        pol.addPoint(position.x + width, position.y + height + pillarWidth + getGapSize());

        //plug bottom
        pol.addPoint(position.x + step * 4, position.y + height + pillarWidth + getGapSize());
        pol.addPoint(position.x + step * 3, position.y + height + pillarWidth + getGapSize() + step);
        pol.addPoint(position.x + step * 2, position.y + height + pillarWidth + getGapSize());

        pol.addPoint(position.x, position.y + height + pillarWidth + getGapSize());
        return pol;
    }

    /**
     * Iterates through the Conditions, adding them to a list.
     */
    private void fillConditions(){
        conditions.clear();
        //Fill conditions
        ConditionBlockModel nextCondition = firstCondition;
        while(nextCondition != null){
            conditions.add(nextCondition);
            nextCondition = (ConditionBlockModel) nextCondition.nextBlock;
        }
    }

    /**
     * @return conditionlist is not empty, no NOT at the end and no predicate before the end
     */
    protected boolean checkConditions(){
        fillConditions();

        //Check conditions
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

    protected int getConditionWidth(){return conditionWidth;}

    /**
     * Is the given position on this statement block.
     * This method is overridden bcs statementBlocks should only be clicked on their upper part (conditionWidth).
     * @param x the x position to check
     * @param y the y position to check
     */
    @Override
    public boolean isWithin(int x, int y) {
        return (x > getPosition().x && x < getPosition().x + conditionWidth) && (y > getPosition().y && y < getPosition().y + getHeight());
    }

    /**
     * Updates the positions of the connectors
     */
    @Override
    public void updateConnectors() {
        super.updateConnectors();
        if(conditionConnector == null) return;
        conditionConnector.setPosition(pointSum(position,conditionOffset));
        if(nextConnector == null) return;
        Point gapOffset = new Point(0,getHeight());
        nextConnector.setPosition(pointSum(position,gapOffset));
    }

    /**
     * Replaces the connectors with new versions.
     */
    @Override
    public void renewConnectors() {
        super.renewConnectors();
        if(conditionConnector == null) return;
        conditionConnector = new Connector(pointSum(position,conditionOffset),ConnectorType.CONDITION);
        if(nextConnector == null) return;
        Point gapOffset = new Point(0,getHeight());
        nextConnector.setPosition(pointSum(position,gapOffset));
    }

    /**
     * Is the given block connected to this block by one of its connectors?
     * @param blockModel the block to check for
     * @return the boolean answer
     */
    @Override
    public boolean hasConnectedBlock(BlockModel blockModel) {
        return super.hasConnectedBlock(blockModel) || blockModel == firstCondition;
    }

    public void setFirstCondition(ConditionBlockModel firstCondition) {
        this.firstCondition = firstCondition;
    }

    protected ConditionBlockModel getFirstCondition(){
        return firstCondition;
    }
}