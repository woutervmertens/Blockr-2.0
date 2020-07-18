package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

import java.awt.*;

public class WhileBlock extends StatementBlock{
    public WhileBlock(WhileBlockModel model){
        super(model);
        //model = new WhileBlockModel(new StdBlockData(position, width, height,"WHILE"));
    }

    @Override
    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {
        if(super.isConditionValid(gw)){
            b.AddBlockToProgramFront(model);
            b.AddBlockGroupToProgramFront(model.bodyBlockModels);
        }
        return SuccessState.SUCCESS;
    }
}
