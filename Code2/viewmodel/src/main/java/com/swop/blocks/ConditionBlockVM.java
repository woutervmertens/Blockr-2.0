package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

import java.awt.*;

/**
 * The Logic for a Condition Block.
 */
public class ConditionBlockVM extends BlockVM {
    public ConditionBlockVM(ConditionBlockModel model){
        super(model);
    }

    @Override
    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {
        return null;
    }

    /**
     * Returns a Connector for which the position and BlockModelType are valid or returns null.
     * @param position the position to check
     * @param blockModelType the BlockModelType to check
     * @return a Connector or null
     */
    @Override
    public Connector getConnectorOrNull(Point position, BlockModelType blockModelType){
        if(model.nextConnector == null) return null;
        //Not condition or funcdef
        if(blockModelType == BlockModelType.CONDITION && model.nextConnector.isOnConnector(position))
            return model.nextConnector;
        return null;
    }
}
