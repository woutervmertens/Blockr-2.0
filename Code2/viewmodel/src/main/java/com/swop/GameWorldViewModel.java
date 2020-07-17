package com.swop;

import com.swop.blocks.Block;

import java.awt.*;

public class GameWorldViewModel extends ViewModel {
    GameWorldModel model;

    public GameWorldViewModel(Point pos, int width, int height) {
        super(pos, width, height);
    }

    @Override
    public void HandleMousePress(int x, int y) {
        if(!isWithin(x,y)) return;
        //TODO: scrollbar
    }

    @Override
    public void HandleMouseRelease(Block draggedBlock, int x, int y) {
        return;
        //TODO: scrollbar
    }

    @Override
    public void HandleMouseDrag(int x, int y) {
        return;
        //TODO: scrollbar
    }

    public void paint(Graphics g){
        model.gameWorld.paint(g, position);
    }
}
