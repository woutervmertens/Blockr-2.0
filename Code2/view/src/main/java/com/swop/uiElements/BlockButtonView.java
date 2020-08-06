package com.swop.uiElements;

import com.swop.BlockButton;

import java.awt.*;

public class BlockButtonView {
    private BlockButton viewModel;
    private BlockView block;

    public BlockButtonView(BlockButton viewModel){
        this.viewModel = viewModel;
        block = new BlockView(viewModel.getBlock());
    }

    /**
     * Draws the BlockButton.
     * @param g Graphics object
     */
    public void draw(Graphics g){
        if(viewModel == null) return;
        g.setColor(viewModel.getBGColor());
        g.fillPolygon(viewModel.getBGPolygon());
        g.setColor(Color.BLACK);
        g.drawPolygon(viewModel.getBGPolygon());
        block.draw(g);
    }
}
