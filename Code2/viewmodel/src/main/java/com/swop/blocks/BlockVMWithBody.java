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
        /*BlockModel bm = getFirstBodyBlock();
        while (bm != null){
            setFirstBodyBlock(bm.getNext());
            BlockVM blockVM = BlockFactory.getInstance().createBlockVM(bm);
            blockVM.Remove(model);
            bm = getFirstBodyBlock();
        }*/
        setFirstBodyBlock(null);
        super.Remove(parent);
    }

    @Override
    public void replaceChild(BlockModel model) {
        super.replaceChild(model);
        if(getFirstBodyBlock() == model)
            setFirstBodyBlock(model.getNext());
    }

    @Override
    public void updatePosition(Point ConnectorPos) {
        super.updatePosition(ConnectorPos);
        int gap = 0;
        for (BlockModel bm : ((BlockModelWithBody)model).getBodyBlockModels())
        {
            BlockVM blockVM = BlockFactory.getInstance().createBlockVM(bm);
            gap += blockVM.getHeight();
        }
        ((BlockModelWithBody)model).setGapSize(gap);
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
