package com.swop;

import com.swop.scrollbar.ScrollbarModel;

import java.awt.*;

/**
 * The logic for the ScrollBar View.
 */
public class ScrollBarViewModel extends ViewModel{
    ScrollbarModel model;
    private boolean isDragging = false;
    public ScrollBarViewModel(Point pos, int height, int width) {
        super(pos, width, height);
        model = new ScrollbarModel(position,height,width);
    }

    /**
     * React to a MousePress on (x,y):
     *  if the scrollbar is active and (x,y) is on the scrollbar:
     *  if it's on the handle, start dragging
     *  else if the click is above the handle move it up a bit
     *  else the click is below the handle, move it down a bit
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
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

    /**
     * React to a MouseRelease on (x,y):
     *  if the scrollbar is active stop dragging
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
    @Override
    public void HandleMouseRelease(int x, int y) {
        if(!model.isActive()) return;
        isDragging = false;
    }

    /**
     * React to a MouseDrag on (x,y):
     *  if the scrollbar is active and we're dragging, change the handle position accordingly
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
    @Override
    public void HandleMouseDrag(int x, int y) {
        if(!model.isActive()) return;
        if(isDragging) setHandleYPosition(normalize(y));
    }

    /**
     * Does nothing.
     */
    @Override
    public void HandleReset() {}

    /**
     * Change the handle y position, within limits.
     * @param y the new y position (normalised)
     */
    private void setHandleYPosition(float y){
        if(y < 0) y = 0.0f;
        if(y > (1.0f)) y = 1.0f;
        model.setHandleYPosition(y);
    }

    /**
     * Does nothing.
     * @return null
     */
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

    /**
     * Normalises an int for the height of the scrollbar
     * @param yValue an int to normalise
     * @return the normalised float
     */
    private float normalize(int yValue){
        int min = model.getPosition().y;
        int max = model.getHeight() - model.getHandleHeight();
        float ret = (((float)yValue - min) / (max - min));
        return ret;
    }

    /**
     * Changes the size of the handle with start height divided by the current height of the scrollbar
     * @param totalHeight the current height of the scrollbar
     */
    public void updateHandleHeight(int totalHeight){
        float y = ((float)getHeight()/totalHeight)*(2.0f/3.0f);
        model.setHandleHeight(y);
    }
}
