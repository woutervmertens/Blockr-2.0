package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

import java.awt.*;

public class ConditionBlockVM extends BlockVM {
    public ConditionBlockVM(ConditionBlockModel model){
        super(model);
    }

    @Override
    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {
        return null;
    }

    @Override
    public Connector getConnectorOrNull(Point position, BlockModelType blockModelType){
        if(model.nextConnector == null) return null;
        //Not condition or funcdef
        if(blockModelType == BlockModelType.CONDITION && model.nextConnector.isOnConnector(position))
            return model.nextConnector;
        return null;
    }
}
