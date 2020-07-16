package com.swop;

import com.swop.blocks.Block;
import com.swop.blocks.BlockModel;
import com.swop.handlers.BlockrGameFacade;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class PaletteViewModel extends ViewModel {
    private PaletteModel model;
    public PaletteViewModel(Point pos, int width, int height, BlockrGameFacade gameController) {
        super(pos, width, height);
        fillModelWithSupportedBlocks(gameController);
    }

    private void fillModelWithSupportedBlocks(BlockrGameFacade gameController) {
        //TODO
    }

    public boolean isHidden(){
        return model.isHidden();
    }

    public Collection<Block> getAllBlocks(){
        Collection<BlockModel> bms = model.getSupportedBlocks();
        Collection<Block> bs = new ArrayList<>();
        for (BlockModel bm : bms){
            bs.add(new Block(bm));
        }
        return bs;
    }

    /**
     * Get type of the area clicked on (assuming that the click is on the palette area).
     *
     * @custom.pre isWithin(x, y);
     * @param x Click x coordinate.
     * @param y Click y coordinate.
     * @return The Block of the clicked element.
     */
    public Block getBlockClicked(int x, int y) {
        assert isWithin(x, y);

        Collection<BlockModel> bms = model.getSupportedBlocks();
        for (BlockModel bm : bms){
            if(bm.isWithin(x,y)) return new Block(bm);
        }
        return null;
    }

    @Override
    public void HandleClick(int x, int y) {
        if(!isWithin(x,y)) return;
        //TODO
    }
}
