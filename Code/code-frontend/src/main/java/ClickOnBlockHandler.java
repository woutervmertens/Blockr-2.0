import UIElements.BlockTypes;

public class ClickOnBlockHandler {
    //create a block at pos(x,y) of BlockType type
    public void createBlock(BlockTypes type, int x, int y)
    {
        System.out.println("BlockType: " + type.toString());
    }
}
