package com.swop.uiElements;

import com.swop.blocks.Block;
import com.swop.blocks.BlockViewData;

import java.awt.*;

public class UIBlock {
    private Block viewModel;

    public UIBlock(Block viewModel){this.viewModel = viewModel;}

    public void draw(Graphics g){
        if(viewModel == null) return;
        BlockViewData data = viewModel.getViewData();
        g.setColor(data.getColor());
        g.fillPolygon(data.getPolygon());
        g.setColor(Color.BLACK);
        g.drawString(data.getText(),data.getTextPosition().x,data.getTextPosition().y);
    }
}
