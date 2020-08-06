package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

/**
 * The Logic for a Function Definition Block.
 */
public class FunctionDefinitionBlockVM extends BlockVMWithBody {

    public FunctionDefinitionBlockVM(FunctionDefinitionBlockModel model) {
        super(model);
    }

    /**
     * Adds all models in the body to the front of the program.
     * @param gw the GameWorld
     * @param b the ProgramAreaModel
     * @return SuccessState.SUCCESS
     */
    @Override
    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {
        b.AddBlockGroupToProgramFront(((FunctionDefinitionBlockModel)model).getBodyBlockModels());
        return SuccessState.SUCCESS;
    }
}
