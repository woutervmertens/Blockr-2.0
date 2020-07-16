package com.swop;

import com.swop.blocks.Block;
import com.swop.uiElements.UIBlock;

import java.awt.*;

public class PaletteView extends View{
    public PaletteView(Point pos, int width, int height, GameController gameController){
        viewModel = new PaletteViewModel(pos, width, height,gameController);
    }

    /**
     * Paints the available buttons
     *
     * @param g Graphics Objects
     */
    public void draw(Graphics g)
    {
        //Background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(viewModel.getPosition().x, viewModel.getPosition().y, viewModel.getWidth(), viewModel.getHeight());

        //Hide if needed
        if (((PaletteViewModel)viewModel).isHidden()) return;

        //Get data
        for (Block block : ((PaletteViewModel)viewModel).getAllBlocks()) {
            UIBlock b = new UIBlock(block);
            b.draw(g);
        }
    }
}
