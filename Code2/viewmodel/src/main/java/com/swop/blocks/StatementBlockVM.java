package com.swop.blocks;

import com.swop.GameWorld;

import java.awt.*;

/**
 * The base logic class for Statement Blocks.
 */
public abstract class StatementBlockVM extends BlockVMWithBody {

    public StatementBlockVM(StatementBlockModel model) {
        super(model);
        assert(model != null);
    }


    /**
     * Checks all Conditions if they are valid together
     * @param gw the GameWorld
     * @return Returns true or false according to the combination of conditions are true or false.
     */
    protected boolean isConditionValid(GameWorld gw) {
        if(!((StatementBlockModel)model).checkConditions()) return false;
        int size = ((StatementBlockModel)model).conditions.size();
        ConditionBlockModel last = ((StatementBlockModel)model).conditions.get(size - 1);
        // if length is even then there is an odd number of not blocks -> opposite of the result of wallInFront(world)
        if (size % 2 == 0) return !gw.evaluate(last.getPredicate());
        else if (last.isPredicate()) return gw.evaluate(last.getPredicate());
        else return false;
    }

    /**
     * Unlinks the first condition then calls parent method.
     * @param parent parent BlockModel
     */
    @Override
    public void Remove(BlockModel parent) {
        setFirstCondition(null);
        super.Remove(parent);
    }

    /**
     * Calls parent method and handles first condition.
     * @param model old child
     */
    @Override
    public void replaceChild(BlockModel model) {
        super.replaceChild(model);
        if(getFirstCondition() == model)
            setFirstCondition(model.getNext());
    }

    public BlockModel getFirstCondition(){
        return ((StatementBlockModel)model).getFirstCondition();
    }

    public void setFirstCondition(BlockModel block){
        ((StatementBlockModel)model).setFirstCondition((ConditionBlockModel) block);
    }

    /**
     * Calls parent method, if it returns null; it checks the Connector for the Conditions.
     * @param position the position to check
     * @param blockModelType the BlockModelType to check
     * @return a Connector or null
     */
    @Override
    public Connector getConnectorOrNull(Point position, BlockModelType blockModelType) {
        Connector res = super.getConnectorOrNull(position, blockModelType);
        if (res != null) return res;
        //Only conditions allowed
        if (blockModelType == BlockModelType.CONDITION && ((StatementBlockModel) model).conditionConnector.isOnConnector(position))
            return ((StatementBlockModel) model).conditionConnector;
        return null;
    }
}
