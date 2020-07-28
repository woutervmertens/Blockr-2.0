package com.swop;

import com.swop.scrollbar.ScrollbarModel;

import java.awt.*;

public class ScrollBarViewModel extends ViewModel{
    ScrollbarModel model;
    public ScrollBarViewModel(Point pos, int height, int width) {
        super(pos, width, height);
        model = new ScrollbarModel(position,height,width);
    }

    @Override
    public void HandleMousePress(int x, int y) {

    }

    @Override
    public void HandleMouseRelease(int x, int y) {

    }

    @Override
    public void HandleMouseDrag(int x, int y) {

    }

    @Override
    public void HandleReset() {

    }

    @Override
    public Object getModel() {
        return null;
    }

    public boolean isActive() {
        return model.isActive();
    }

    public void setActive(boolean active) {
        model.setActive(active);
    }

    public Polygon getBGPoly(){
        return model.getBackGroundPoly();
    }

    public Polygon getHandlePoly(){
        return model.getHandlePoly();
    }

    public Color getBGColor(){
        return model.getBGColor();
    }

    public Color getHandleColor(){
        return model.getHandleColor();
    }
}
