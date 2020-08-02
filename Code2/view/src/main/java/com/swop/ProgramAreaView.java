package com.swop;

import com.swop.blocks.BlockVM;
import com.swop.uiElements.BlockView;

import java.awt.*;

public class ProgramAreaView extends View{
    public ProgramAreaView(Point pos, int width, int height, GameController gameController) {
        viewModel = new ProgramAreaViewModel(pos, width, height, gameController);
    }

    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(viewModel.getPosition().x, viewModel.getPosition().y, viewModel.getWidth(), ((ScrollableViewModel)viewModel).getFullHeight());

        for (BlockVM blockVM : ((ProgramAreaViewModel)viewModel).getAllBlocks()) {
            BlockView b = new BlockView(blockVM);
            b.draw(g);
        }
    }
}
