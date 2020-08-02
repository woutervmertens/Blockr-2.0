package com.swop;

import com.swop.scrollbar.ScrollbarModel;

import java.awt.*;

public class ScrollBarViewModel extends ViewModel{
    ScrollbarModel model;
    private boolean isDragging = false;
    public ScrollBarViewModel(Point pos, int height, int width) {
        super(pos, width, height);
        model = new ScrollbarModel(position,height,width);
    }

    @Override
    public void HandleMousePress(int x, int y) {
        if(!model.isActive() || !model.isWithin(x,y)) return;
        if(model.isWithinHandle(x,y)){
            isDragging = true;
        }
        else if(normalize(y) < model.getHandleYPosition()){
            //click above handle
            float hPos = model.getHandleYPosition() - 0.1f;
            setHandleYPosition(hPos);
        }
        else{
            //click below handle
            float hPos = model.getHandleYPosition() + 0.1f;
            setHandleYPosition(hPos);
        }
    }

    @Override
    public void HandleMouseRelease(int x, int y) {
        if(!model.isActive()) return;
        isDragging = false;
    }

    @Override
    public void HandleMouseDrag(int x, int y) {
        if(!model.isActive()) return;
        if(isDragging) setHandleYPosition(normalize(y));
    }

    @Override
    public void HandleReset() {

    }

    private void setHandleYPosition(float y){
        if(y < 0) y = 0.0f;
        if(y > (1.0f)) y = 1.0f;
        model.setHandleYPosition(y);
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

    public float getNormalizedYPos(){
        return model.getHandleYPosition();
    }

    @Override
    public void setPosition(Point p) {
        model.setPosition(p);
    }

    @Override
    public void setHeight(int height) {
        model.setHeight(height);
    }

    @Override
    public void setWidth(int width) {
        model.setWidth(width);
    }

    @Override
    public Point getPosition() {
        return model.getPosition();
    }

    @Override
    public int getHeight() {
        return model.getHeight();
    }

    @Override
    public int getWidth() {
        return model.getWidth();
    }

    private float normalize(int yValue){
        int min = model.getPosition().y;
        int max = min + model.getHeight() - model.getHandleHeight();
        float ret = (((float)yValue - min) / (max - min));
        return ret;
    }

    public void updateHandleHeight(int totalHeight){
        float y = ((float)getHeight()/totalHeight);
        model.setHandleHeight(y);
    }
}
