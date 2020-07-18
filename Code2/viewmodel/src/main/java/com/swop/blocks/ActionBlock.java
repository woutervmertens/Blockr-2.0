package com.swop.blocks;

import com.swop.Action;
import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

import java.awt.*;

public class ActionBlock extends Block{
    protected ActionBlock(ActionBlockModel model) {
        super(model);
        //super.model = new ActionBlockModel(new StdBlockData(position,width,height,action.toString()),action);
    }

    /**
     * Executes the action on the game world if possible, otherwise the state of the gameworld will restore like the action didn't happen.
     */
    @Override
    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {
        return gw.doAction(((ActionBlockModel)model).getAction());
    }
}
