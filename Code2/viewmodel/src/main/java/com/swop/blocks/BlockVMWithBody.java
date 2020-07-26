package com.swop.blocks;

import java.awt.*;

public abstract class BlockVMWithBody extends BlockVM {


    public BlockVMWithBody(BlockModelWithBody model) {
        super(model);
    }

    public void setPosition(Point position) {
        try {
            int dx = position.x - model.getPosition().x;
            int dy = position.y - model.getPosition().y;
            model.setPosition(position);
            for (BlockModel bodyBlockModel : ((BlockModelWithBody)model).getBodyBlockModels()) {
                bodyBlockModel.setPosition(new Point(bodyBlockModel.getPosition().x + dx, bodyBlockModel.getPosition().y + dy));
            }
        } catch (NullPointerException e) {
            model.setPosition(position);
        }
    }

    /**
     * Calls remove on body, then removes itself
     * @param parent parent BlockModel
     */
    @Override
    public void Remove(BlockModel parent) {
        for (BlockModel bm : ((BlockModelWithBody)model).getBodyBlockModels())
        {
            BlockVM blockVM = BlockFactory.getInstance().createBlockVM(bm);
            blockVM.Remove(parent);
        }
        super.Remove(parent);
    }

    public BlockModel getFirstBodyBlock(){
        return ((BlockModelWithBody)model).getFirstBodyBlockModel();
    }

    public void setFirstBodyBlock(BlockModel block){
        ((BlockModelWithBody)model).setFirstBodyBlockModel(block);
    }

    @Override
    public Connector getConnectorOrNull(Point position) {
        Connector res = super.getConnectorOrNull(position);
        if(res != null) return res;
        if(((BlockModelWithBody)model).bodyConnector.isOnConnector(position))
            return ((BlockModelWithBody)model).bodyConnector;
        return null;
    }
}
