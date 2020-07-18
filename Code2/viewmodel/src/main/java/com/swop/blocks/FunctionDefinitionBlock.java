package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

import java.awt.*;

public class FunctionDefinitionBlock extends BlockWithBody{

    public FunctionDefinitionBlock(FunctionDefinitionBlockModel model) {
        super(model);
        //super.model = new FunctionDefinitionBlockModel(new StdBlockData(position, width, height,text));
    }

    @Override
    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {
        b.AddBlockGroupToProgramFront(model.bodyBlockModels);
        return SuccessState.SUCCESS;
    }
}
