package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

public class FunctionCallBlockVM extends BlockVM {

    protected FunctionCallBlockVM(FunctionCallBlockModel model) {
        super(model);
    }
    @Override
    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {
        b.AddBlockToProgramFront(model);
        return SuccessState.SUCCESS;
    }
}
