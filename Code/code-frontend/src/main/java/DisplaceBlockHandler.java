import UIElements.UIBlock;

import java.awt.*;

public class DisplaceBlockHandler {
    private UIProgramArea uiProgramArea;
    private UIPalette uiPalette;
    private ExecuteProgramHandler executeProgramHandler;

    public DisplaceBlockHandler(UIProgramArea uiProgramArea, UIPalette uiPalette, UIGameWorld uiGameWorld) {
        this.uiProgramArea = uiProgramArea;
        this.uiPalette = uiPalette;

        executeProgramHandler = new ExecuteProgramHandler(uiProgramArea, uiGameWorld);
    }

    public void handleRelease(int x, int y, UIBlock block) {
        //TODO: create block where needed
        if (uiProgramArea.isWithin(x, y)) {
            Point dropPos = new Point(x, y);
            //check if close enough to other blocks plug
            int radius = 20;
            UIBlock closeBlock = getBlockWithinRadius(x, y, radius);
            if (closeBlock != null) {
                //TODO: connect this block with the close block
                //TODO: handle backend

            } else {
                //TODO: create new program in backend
                executeProgramHandler.reset();
            }
        } else {
            //TODO: backend: remove currently selected block
            uiPalette.setHidden(false);
        }
    }

    private UIBlock getBlockWithinRadius(int x, int y, int radius) {
        for (UIBlock b : uiProgramArea.getBlocks()) {
            if (getDistance(x, y, b.getPosition()) < radius)  //TODO: make this condition more precise
                for (Point con : b.getConnectionPoints()) {
                    return b;
                }
        }
        return null;
    }

    /**
     * Get the distance between two given points.
     * TODO: why int and not double ?
     *
     * @param x X-value of point1
     * @param y Y-value of point1
     * @param p Point2
     */
    private int getDistance(int x, int y, Point p) {
        return (int) Math.sqrt((p.getX() - x) * (p.getX() - x) + (p.getY() - y) * (p.getY() - y));
    }
}
