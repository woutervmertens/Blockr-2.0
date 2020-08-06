package com.swop.uiElements;

import com.swop.BlockButtonViewModel;

import java.awt.*;

/**
 * The ui for a button in the palette.
 */
public class BlockButtonView {
    private BlockButtonViewModel viewModel;
    private BlockView block;

    public BlockButtonView(BlockButtonViewModel viewModel){
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
