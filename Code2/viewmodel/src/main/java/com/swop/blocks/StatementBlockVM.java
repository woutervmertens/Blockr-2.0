package com.swop.blocks;

import com.swop.GameWorld;

import java.awt.*;

public abstract class StatementBlockVM extends BlockVMWithBody {

    public StatementBlockVM(StatementBlockModel model) {
        super(model);
        assert(model != null);
    }


    /**
     * @return Returns true or false according to the combination of conditions are true or false.
     *
     * @param gw the GameWorld
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

    @Override
    public void Remove(BlockModel parent) {
        /*for (BlockModel bm : ((StatementBlockModel)model).getConditions())
        {
            BlockVM blockVM = BlockFactory.getInstance().createBlockVM(bm);
            blockVM.Remove(parent);
        }*/
        setFirstCondition(null);
        super.Remove(parent);
    }

    @Override
    public void replaceChild(BlockModel model) {
        super.replaceChild(model);
        if(getFirstCondition() == model)
            setFirstCondition(model.getNext());
    }

    public BlockModel getFirstCondition(){
        return ((StatementBlockModel)model).getFirstBodyBlockModel();
    }

    public void setFirstCondition(BlockModel block){
        ((StatementBlockModel)model).setFirstCondition((ConditionBlockModel) block);
    }

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
