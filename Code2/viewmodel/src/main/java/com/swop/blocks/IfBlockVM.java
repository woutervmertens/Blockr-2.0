package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

public class IfBlockVM extends StatementBlockVM {
    public IfBlockVM(IfBlockModel model){
        super(model);
    }

    /**
     * If the conditions are valid; Adds all models in the body to the front of the program.
     * @param gw the GameWorld
     * @param b the ProgramAreaModel
     * @return return SuccessState.SUCCESS
     */
    @Override
    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {
        if(super.isConditionValid(gw)){
            b.AddBlockGroupToProgramFront(((IfBlockModel)model).getBodyBlockModels());
        }
        return SuccessState.SUCCESS;
    }
}
