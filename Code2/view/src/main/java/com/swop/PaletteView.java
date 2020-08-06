package com.swop;

import com.swop.uiElements.BlockButtonView;

import java.awt.*;

/**
 * The ui for the Palette Window.
 */
public class PaletteView extends View{
    public PaletteView(Point pos, int width, int height, WindowGameControllerFacade gameController){
        super.viewModel = new PaletteViewModel(pos, width, height,gameController);
    }

    /**
     * Paints the available buttons and background
     *
     * @param g Graphics Objects
     */
    public void draw(Graphics g)
    {
        //Background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(viewModel.getPosition().x, viewModel.getPosition().y, viewModel.getWidth(), ((ScrollableViewModel)viewModel).getFullHeight());

        //Hide if needed
        if (((PaletteViewModel)viewModel).isHidden()) return;

        //Get data
        for (BlockButtonViewModel blockBtn : ((PaletteViewModel)viewModel).getAllButtons()) {
            BlockButtonView btnView = new BlockButtonView(blockBtn);
            btnView.draw(g);
        }
    }
}
