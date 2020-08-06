package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

/**
 * The Logic for a Function Call Block.
 */
public class FunctionCallBlockVM extends BlockVM {

    protected FunctionCallBlockVM(FunctionCallBlockModel model) {
        super(model);
    }

    /**
     * Calls Execute on the referenced FunctionDefinition.
     * @param gw the GameWorld
     * @param b the ProgramAreaModel
     * @return SuccessState.Success
     */
    @Override
    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {
        BlockModel def = ((FunctionCallBlockModel)model).getDefinitionBlock();
        if(def != null) {
            FunctionDefinitionBlockVM defVM = (FunctionDefinitionBlockVM) BlockFactory.getInstance().createBlockVM(def);
            defVM.Execute(gw, b);
        }
        return SuccessState.SUCCESS;
    }
}
