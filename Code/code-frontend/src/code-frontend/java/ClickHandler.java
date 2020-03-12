import UIElements.BlockTypes;
import UIElements.UIBlock;

public class ClickHandler {
    private UIPalette uiPalette;
    private UIProgramArea uiProgramArea;
    private ClickOnBlockHandler clickOnBlockHandler = new ClickOnBlockHandler();

    public ClickHandler(UIPalette palette, UIProgramArea programArea) {
        uiPalette = palette;
        this.uiProgramArea = programArea;
    }

    public UIBlock getUIBlock(int x, int y) {
        if (uiPalette.isWithin(x, y) && !uiPalette.isHidden()) {
            BlockTypes type = uiPalette.getUiBlockClicked(x,y).getType();
            return type.getNewUIBlock(x, y);
//            BlockTypes type = uiPalette.getBlockTypeClicked(x, y);
        } else if (uiProgramArea.isWithin(x, y)) {
            return uiProgramArea.getUiBlockClicked(x, y);
        }
        return null;
    }
}
