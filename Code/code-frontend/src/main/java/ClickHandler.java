public class ClickHandler {
    UIPalette UIPalette;
    ClickOnBlockHandler clickOnBlockHandler = new ClickOnBlockHandler();

    public ClickHandler(UIPalette p)
    {
        UIPalette = p;
    }

    public void handleClick(int x, int y){
        if(x <= UIPalette.getWidth()) {
            BlockTypes bt = UIPalette.getBlockTypeClicked(y);
            if(bt == BlockTypes.INVALIDTYPE) System.out.println("not valid");
            else
                clickOnBlockHandler.createBlock(bt,x,y);
        }
    }
}
