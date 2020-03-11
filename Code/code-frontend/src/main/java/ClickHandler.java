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
            BlockTypes bt = uiPalette.getBlockTypeClicked(y);
            if (bt == BlockTypes.INVALIDTYPE) System.out.println("not valid");
            else
                return clickOnBlockHandler.createBlock(bt, x, y, uiPalette.getWidth() - 20, uiPalette.getBlockHeight());
        } else if (uiProgramArea.isWithin(x, y)) {
            //TODO: click in ProgramArea (on block --> displace)
        }
        return null;
    }
}
