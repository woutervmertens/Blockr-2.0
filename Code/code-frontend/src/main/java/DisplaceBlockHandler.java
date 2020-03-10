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

    public void handleRelease(int x, int y, UIBlock block){
        //TODO: create block where needed
        if(uiProgramArea.isWithin(x,y))
        {
            Point dropPos = new Point(x,y);
            //TODO: check if close enough to other blocks plug, if so: snap into place and handle backend

            //TODO: else create new program in backend (and dissable execution i think)
        }
        else
        {
            //TODO: backend: remove currently selected block
        }
    }
}
