package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

import java.awt.*;

public class FunctionCallBlock extends Block{

    protected FunctionCallBlock(FunctionCallBlockModel model) {
        super(model);
        //model = new FunctionCallBlockModel(new StdBlockData(position,width,height,definitionBlock.getText()),definitionBlock);
    }
    @Override
    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {
        b.AddBlockToProgramFront(model);
        return SuccessState.SUCCESS;
    }
}
