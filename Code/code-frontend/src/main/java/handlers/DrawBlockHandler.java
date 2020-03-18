package handlers;

import uiElements.UIBlock;

import java.awt.*;

public class DrawBlockHandler {
    // TODO: should rather the UIBlocks draw themselves ? Or just delete the UIBlocks
    public void draw(UIBlock block, Graphics g, boolean isHighlight)
    {
        g.setColor(block.getColor(isHighlight));
        g.fillPolygon(block.getPolygon());
        g.setColor(Color.BLACK);
        g.drawString(block.getText(), block.getTextPosition().x, block.getTextPosition().y);
    }
}
