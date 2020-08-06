package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

public class WhileBlockVM extends StatementBlockVM {
    public WhileBlockVM(WhileBlockModel model){
        super(model);
    }

    /**
     * If the conditions are valid; Adds itself and all models in the body to the front of the program.
     * @param gw the GameWorld
     * @param b the ProgramAreaModel
     * @return return SuccessState.SUCCESS
     */
    @Override
    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {
        if(super.isConditionValid(gw)){
            b.AddBlockToProgramFront(model);
            b.AddBlockGroupToProgramFront(((WhileBlockModel)model).getBodyBlockModels());
        }
        return SuccessState.SUCCESS;
    }
}
