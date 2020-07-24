package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

public class IfBlockVM extends StatementBlockVM {
    public IfBlockVM(IfBlockModel model){
        super(model);
        //model = new IfBlockModel(new StdBlockData(position, width, height,"IF"));
    }

    @Override
    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {
        if(super.isConditionValid(gw)){
            b.AddBlockGroupToProgramFront(model.bodyBlockModels);
        }
        return SuccessState.SUCCESS;
    }
}
