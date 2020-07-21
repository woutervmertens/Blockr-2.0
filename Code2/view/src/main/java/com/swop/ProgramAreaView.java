package com.swop;

import com.swop.blocks.Block;
import com.swop.uiElements.UIBlock;

import java.awt.*;

public class ProgramAreaView extends View{
    public ProgramAreaView(Point pos, int width, int height, GameController gameController) {
        viewModel = new ProgramAreaViewModel(pos, width, height, gameController);
    }

    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(viewModel.getPosition().x, viewModel.getPosition().y, viewModel.getWidth(), viewModel.getHeight());

        for (Block block : ((ProgramAreaViewModel)viewModel).getAllBlocks()) {
            UIBlock b = new UIBlock(block);
            b.draw(g);
        }
    }
}
