package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

public class IfBlockVM extends StatementBlockVM {
    public IfBlockVM(IfBlockModel model){
        super(model);
    }

    @Override
    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {
        if(super.isConditionValid(gw)){
            b.AddBlockGroupToProgramFront(model.getBodyBlockModels());
        }
        return SuccessState.SUCCESS;
    }
}
