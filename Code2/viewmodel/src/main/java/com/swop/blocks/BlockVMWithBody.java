package com.swop.blocks;

import java.awt.*;

public abstract class BlockVMWithBody extends BlockVM {


    public BlockVMWithBody(BlockModelWithBody model) {
        super(model);
    }

    /**
     * Calls remove on body, then removes itself
     * @param parent parent BlockModel
     */
    @Override
    public void Remove(BlockModel parent) {
        setFirstBodyBlock(null);
        super.Remove(parent);
    }

    /**
     * Calls parent method and handles first child in body.
     * @param model old child
     */
    @Override
    public void replaceChild(BlockModel model) {
        super.replaceChild(model);
        if(getFirstBodyBlock() == model)
            setFirstBodyBlock(model.getNext());
    }

    /**
     * Calls parent method and the gap size.
     * @param ConnectorPos the new position
     */
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

    /**
     * Calls parent method, if it returns null; it checks the Connector for the body.
     * @param position the position to check
     * @param blockModelType the BlockModelType to check
     * @return a Connector or null
     */
    @Override
    public Connector getConnectorOrNull(Point position, BlockModelType blockModelType) {
        Connector res = super.getConnectorOrNull(position, blockModelType);
        if(res != null) return res;
        //Not condition or funcdef
        if(!(blockModelType == BlockModelType.CONDITION || blockModelType == BlockModelType.FUNCDEF) && ((BlockModelWithBody)model).bodyConnector.isOnConnector(position))
            return ((BlockModelWithBody)model).bodyConnector;
        return null;
    }
}
