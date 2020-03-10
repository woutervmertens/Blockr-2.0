
import UIElements.UIBlock;

import java.awt.*;
import java.util.ArrayList;

public class UIProgramArea {
    private ArrayList<UIBlock> blocks = new ArrayList<>();
    private int highlightedBlockNumber = -1;

    public UIProgramArea(){

    }

    /**
     * Draws the backdrop and the blocks
     * @param g The Graphics element
     * @param pos The position of the ProgramArea
     */
    public void draw(Graphics g, Point pos)
    {
        g.setColor(Color.PINK);
        g.fillRect(pos.x,pos.y,200,600);

        int i = 0;
        for (UIBlock block : blocks)
        {
            g.setColor(block.getColor(highlightedBlockNumber == i++));
            g.fillPolygon(block.getPolygon());
            g.drawString(block.getText(),block.getPosition().x + 10, block.getPosition().y + 10);
        }
    }

    public void increaseHighlightedBlockNumber()
    {
        highlightedBlockNumber++;
    }

    public void Reset()
    {
        highlightedBlockNumber = -1;
    }
}
