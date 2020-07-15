package com.swop.blocks;

import com.swop.GameWorld;

import java.awt.*;

public abstract class StatementBlock extends BlockWithBody{

    public StatementBlock(StatementBlockModel model) {
        super(model);
    }

    /**
     * Sets the previous position of the statementBlock and sets all the bodyBlocks of the statementBlock to their previous position.
     * @param previousDropPosition The given previous drop position
     */
    public void setPreviousDropPosition(Point previousDropPosition) {
        model.setPreviousDropPosition(previousDropPosition);
        for (BlockModel bodyBlockModel : model.getBodyBlockModels()) {
            bodyBlockModel.setPreviousDropPosition(bodyBlockModel.getPosition());
        }
        for (BlockModel condition : ((StatementBlockModel)model).getConditions()) {
            condition.setPreviousDropPosition(condition.getPosition());
        }
    }

    /**
     * Sets the position of the statementBlock to the given position and the conditionBlock(s) to their adjusted position.
     * @param position the given position.
     */
    @Override
    public void setPosition(Point position) {
        try {
            int dx = position.x - model.getPosition().x;
            int dy = position.y - model.getPosition().y;
            super.setPosition(position);
            for (BlockModel conditionBlockModel : ((StatementBlockModel)model).getConditions()) {
                conditionBlockModel.setPosition(new Point(conditionBlockModel.getPosition().x + dx, conditionBlockModel.getPosition().y + dy));
            }
        } catch (NullPointerException e) {
            super.setPosition(position);
        }
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
}
