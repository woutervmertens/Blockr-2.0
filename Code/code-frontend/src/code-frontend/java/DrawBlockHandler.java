import UIElements.UIBlock;

import java.awt.*;

public class DrawBlockHandler {
    public void draw(UIBlock block, Graphics g)
    {
        g.setColor(block.getColor(highlightedBlockNumber == i++));
        g.fillPolygon(block.getPolygon());
        g.setColor(Color.BLACK);
        g.drawString(block.getText(), block.getTextPosition().x, block.getTextPosition().y);
        switch (block.getType()) {
            case IfStatement:
            case WhileStatement:
                //TODO: statement children/conditions
                //draw conditions
                //draw children
                break;
        }
    }
}
