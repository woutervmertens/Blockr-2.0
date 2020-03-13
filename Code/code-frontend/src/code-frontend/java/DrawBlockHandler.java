import UIElements.UIBlock;

import java.awt.*;

public class DrawBlockHandler {
    public void draw(UIBlock block, Graphics g, boolean isHighlight)
    {
        g.setColor(block.getColor(isHighlight));
        g.fillPolygon(block.getPolygon());
        g.setColor(Color.BLACK);
        g.drawString(block.getText(), block.getTextPosition().x, block.getTextPosition().y);
    }
}
