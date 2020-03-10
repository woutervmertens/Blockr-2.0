import UIElements.BlockTypes;
import UIElements.UIBlock;

public class ClickHandler {
    UIPalette UIPalette;
    ClickOnBlockHandler clickOnBlockHandler = new ClickOnBlockHandler();

    public ClickHandler(UIPalette p)
    {
        UIPalette = p;
    }

    public UIBlock handleClick(int x, int y){
        if(x <= UIPalette.getWidth()) {
            BlockTypes bt = UIPalette.getBlockTypeClicked(y);
            if(bt == BlockTypes.INVALIDTYPE) System.out.println("not valid");
            else
                return clickOnBlockHandler.createBlock(bt,x,y,UIPalette.getWidth() - 20,UIPalette.getBlockheight());
        }
        return null;
    }
}
