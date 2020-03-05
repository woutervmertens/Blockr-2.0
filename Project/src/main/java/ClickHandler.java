public class ClickHandler {
    Palette palette;
    ClickOnBlockHandler clickOnBlockHandler = new ClickOnBlockHandler();

    public ClickHandler(Palette p)
    {
        palette = p;
    }

    public void handleClick(int x, int y){
        if(x <= palette.getWidth()) {
            BlockTypes bt = palette.getBlockTypeClicked(y);
            if(bt == BlockTypes.INVALIDTYPE) System.out.println("not valid");
            else
                clickOnBlockHandler.createBlock(bt,x,y);
        }
    }
}
