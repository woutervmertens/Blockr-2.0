package com.swop;

import com.swop.blocks.BlockVM;
import com.swop.uiElements.BlockView;

import java.awt.*;

/**
 * The ui for the ProgramArea Window.
 */
public class ProgramAreaView extends View{
    public ProgramAreaView(Point pos, int width, int height, WindowGameControllerFacade gameController) {
        viewModel = new ProgramAreaViewModel(pos, width, height, gameController);
    }

    /**
     * Paints the background and all the blocks in the Program Area
     *
     * @param g Graphics Objects
     */
    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(viewModel.getPosition().x, viewModel.getPosition().y, viewModel.getWidth(), ((ScrollableViewModel)viewModel).getFullHeight());

        for (BlockVM blockVM : ((ProgramAreaViewModel)viewModel).getAllBlocks()) {
            BlockView b = new BlockView(blockVM);
            b.draw(g);
        }
    }
}
