package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

public class ConditionBlockVM extends BlockVM {
    public ConditionBlockVM(ConditionBlockModel model){
        super(model);
        //super.model = new ConditionBlockModel(new StdBlockData(position,width,height,(isPredicate) ? predicate.toString(): "NOT"), isPredicate,  predicate);
    }

    @Override
    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {
        return null;
    }
}
