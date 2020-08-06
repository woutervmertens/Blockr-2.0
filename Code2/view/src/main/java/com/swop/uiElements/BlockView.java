package com.swop.uiElements;

import com.swop.blocks.BlockVM;
import com.swop.blocks.BlockViewData;

import java.awt.*;

/**
 * The ui for a block.
 */
public class BlockView {
    private BlockVM viewModel;

    public BlockView(BlockVM viewModel){this.viewModel = viewModel;}

    /**
     * Draws the Block.
     * @param g Graphics object
     */
    public void draw(Graphics g){
        if(viewModel == null) return;
        BlockViewData data = viewModel.getViewData();
        g.setColor(data.getColor());
        g.fillPolygon(data.getPolygon());
        g.setColor(Color.BLACK);
        g.drawString(data.getText(),data.getTextPosition().x,data.getTextPosition().y);
    }
}
