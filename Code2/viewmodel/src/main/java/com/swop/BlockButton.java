package com.swop;

import com.swop.blocks.BlockVM;
import com.swop.blocks.BlockFactory;

import java.awt.*;

public class BlockButton {
    private BlockButtonModel model;
    private GameController gameController;

    public BlockButton(BlockButtonModel model, GameController gameController) {
        this.model = model;
        this.gameController = gameController;
    }

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
