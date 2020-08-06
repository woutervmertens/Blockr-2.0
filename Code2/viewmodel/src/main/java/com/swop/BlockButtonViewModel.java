package com.swop;

import com.swop.blocks.BlockVM;
import com.swop.blocks.BlockFactory;

import java.awt.*;

/**
 * The logic for a button in the palette.
 */
public class BlockButtonViewModel {
    private BlockButtonModel model;
    private WindowGameControllerFacade gameController;

    public BlockButtonViewModel(BlockButtonModel model, WindowGameControllerFacade gameController) {
        this.model = model;
        this.gameController = gameController;
    }

    /**
     * React to a MouseClick on (x,y):
     *  Calls GameController to set dragged block to clone of respective BlockModel.
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
    public void HandleClick(int x, int y){
        if(!model.isWithin(x,y)) return;
        gameController.setDraggedBlockVM(model.getBlockModel().clone());
    }

    public Polygon getBGPolygon(){
        return model.getBGPolygon();
    }

    public Color getBGColor() {
        return model.getBGColor();
    }

    public BlockVM getBlock() {
        return BlockFactory.getInstance().createBlockVM(model.getBlockModel().clone());
    }
}
