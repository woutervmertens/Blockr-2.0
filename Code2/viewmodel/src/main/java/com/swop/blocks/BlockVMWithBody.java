package com.swop.blocks;

import com.swop.ProgramAreaModel;

import java.awt.*;

public abstract class BlockVMWithBody extends BlockVM {
    BlockModelWithBody model;

    public BlockVMWithBody(BlockModelWithBody model) {
        super(model);
    }

    public void setPosition(Point position) {
        try {
            int dx = position.x - model.getPosition().x;
            int dy = position.y - model.getPosition().y;
            model.setPosition(position);
            for (BlockModel bodyBlockModel : model.getBodyBlockModels()) {
                bodyBlockModel.setPosition(new Point(bodyBlockModel.getPosition().x + dx, bodyBlockModel.getPosition().y + dy));
            }
        } catch (NullPointerException e) {
            model.setPosition(position);
        }
    }

    /**
     * Calls remove on body, then removes itself
     * @param b ProgramAreaModel
     */
    @Override
    public void Remove(ProgramAreaModel b) {
        for (BlockModel bm : model.getBodyBlockModels())
        {
            BlockVM blockVM = BlockFactory.getInstance().createBlockVM(bm);
            blockVM.Remove(b);
        }
        super.Remove(b);
    }

    @Override
    public Connector getConnectorOrNull(Point position) {
        Connector res = super.getConnectorOrNull(position);
        if(res != null) return res;
        if(model.bodyConnector.isOnConnector(position))
            return model.bodyConnector;
        return null;
    }
}
