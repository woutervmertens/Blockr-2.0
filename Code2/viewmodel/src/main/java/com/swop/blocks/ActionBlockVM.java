package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

/**
 * The Logic for an Action Block.
 */
public class ActionBlockVM extends BlockVM {
    protected ActionBlockVM(ActionBlockModel model) {
        super(model);
    }

    /**
     * Executes the action on the game world if possible, otherwise the state of the gameworld will restore like the action didn't happen.
     * @param gw the GameWorld
     * @param b the ProgramAreaModel
     */
    @Override
    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {
        return gw.doAction(((ActionBlockModel)model).getAction());
    }
}
