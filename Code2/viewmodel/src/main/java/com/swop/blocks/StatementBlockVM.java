package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;

import java.awt.*;

public abstract class StatementBlockVM extends BlockVMWithBody {

    public StatementBlockVM(StatementBlockModel model) {
        super(model);
    }


    /**
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

    @Override
    public void Remove(ProgramAreaModel b) {
        for (BlockModel bm : ((StatementBlockModel)model).getConditions())
        {
            BlockVM blockVM = BlockFactory.getInstance().createBlockVM(bm);
            blockVM.Remove(b);
        }
        super.Remove(b);
    }

    @Override
    public Connector getConnectorOrNull(Point position) {
        Connector res = super.getConnectorOrNull(position);
        if (res != null) return res;
        if (((StatementBlockModel) model).conditionConnector.isOnConnector(position))
            return ((StatementBlockModel) model).conditionConnector;
        return null;
    }
}
