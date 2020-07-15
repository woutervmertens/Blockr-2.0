package com.swop;

import com.swop.blocks.Block;
import com.swop.uiElements.UIBlock;

import java.awt.*;

//TODO: decorator voor change size en scrollbar
public class ProgramAreaView {
    ProgramAreaViewModel viewModel;
    public ProgramAreaView(Point pos, int width, int height) {
        viewModel = new ProgramAreaViewModel(pos, width, height);
    }

    void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(viewModel.getPosition().x, viewModel.getPosition().y, viewModel.getWidth(), viewModel.getHeight());

        for (Block block : viewModel.getAllBlocks()) {
            UIBlock b = new UIBlock(block);
            b.draw(g);
        }
    }
}
