package com.swop;

import com.swop.blocks.Block;
import com.swop.blocks.BlockContainer;
import com.swop.blocks.BlockModel;
import com.swop.blocks.Connector;

import java.awt.*;
import java.util.List;

public class ProgramAreaViewModel extends ViewModel {
    private BlockContainer blocks = new BlockContainer();
    public ProgramAreaViewModel(Point pos, int width, int height) {
        super(pos, width, height);
    }

    @Override
    public void HandleClick(int x, int y) {
        if(!isWithin(x,y)) return;
    }

    public List<Block> getAllBlocks(){
        return blocks.getAllBlockVMs();
    }

    public void DropBlock(BlockModel blockModel){
        assert isWithin(blockModel.getPosition().x,blockModel.getPosition().y);
        //1.Check AllBlocks for connector link and add block
        //2.Link connectors
        //3.Reorder block positions to fit actual blocks
    }

    public void RemoveBlock(BlockModel blockModel){
        //1.Link block below with block above
        //2.Remove block
        //3.Reorder block positions to fit actual blocks
    }

    private void FixBlockPositions(Connector startConnector){
        //startConnector = lower connector of last correct Block
        //1.Offset position with size of last correct block: Probably best handled in blockModel so it can be overwritten for special cases (such as point 2)
        //2 Offset children
        //3.Repeat until end of block linked list
    }

    public SuccessState ExecuteNext(GameWorld gw){
        return blocks.GetNextToExecute().Execute(gw,blocks);
    }
}
