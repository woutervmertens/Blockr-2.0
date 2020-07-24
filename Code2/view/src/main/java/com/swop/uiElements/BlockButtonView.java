package com.swop.uiElements;

import com.swop.BlockButton;

import java.awt.*;

public class BlockButtonView {
    private BlockButton viewModel;
    private UIBlock block;

    public BlockButtonView(BlockButton viewModel){
        this.viewModel = viewModel;
        block = new UIBlock(viewModel.getBlock());
    }

    public void draw(Graphics g){
        if(viewModel == null) return;
        g.setColor(viewModel.getBGColor());
        g.fillPolygon(viewModel.getBGPolygon());
        g.setColor(Color.BLACK);
        g.drawPolygon(viewModel.getBGPolygon());
        block.draw(g);
    }
}
