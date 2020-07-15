package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.SuccessState;

import java.awt.*;

public class IfBlock extends StatementBlock{
    public IfBlock(IfBlockModel model){
        super(model);
        //model = new IfBlockModel(new StdBlockData(position, width, height,"IF"));
    }

    @Override
    public SuccessState Execute(GameWorld gw, BlockContainer b) {
        if(super.isConditionValid(gw)){
            b.AddBlockGroupToProgramFront(model.bodyBlockModels);
        }
        return SuccessState.SUCCESS;
    }
}
