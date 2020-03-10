import UIElements.BlockTypes;
import UIElements.UIBlock;

import java.awt.*;

public class DisplaceBlockHandler {
    private UIProgramArea uiProgramArea;
    private UIPalette uiPalette;

    public DisplaceBlockHandler(UIProgramArea uiProgramArea, UIPalette uiPalette) {
        this.uiProgramArea = uiProgramArea;
        this.uiPalette = uiPalette;
    }

    private UIBlock closeBlock = null;
    private Point connectionPoint = null;

    public void handleRelease(int x, int y, UIBlock block){
        //TODO: create block where needed
        if(uiProgramArea.isWithin(x,y))
        {
            Point dropPos = new Point(x,y);
            //check if close enough to other blocks plug
            getBlockConnectionsWithinRadius(x,y,20);
            if(closeBlock != null){
                Point newPos = closeBlock.getPosition();
                block.setPosition(connectionPoint);
                //TODO: handle backend

            }else{
                //TODO: create new program in backend (and disable execution i think)
            }
        }
        else
        {
            //TODO: backend: remove currently selected block
            uiPalette.setHidden(false);
        }
    }

    private void getBlockConnectionsWithinRadius(int x, int y, int radius) {
        for (UIBlock b : uiProgramArea.getBlocks()){
            for (Point con : b.getConnectionPoints()) {
                if (getDistance(x, y, con) < radius) {
                    closeBlock = b;
                    connectionPoint = con;
                    return;
                }
            }
        }
        closeBlock = null;
    }

    private int getDistance(int x, int y, Point p) {
        return (int) Math.sqrt((p.getX()-x)*(p.getX()-x) + (p.getY()-y)*(p.getY()-y));
    }
}
