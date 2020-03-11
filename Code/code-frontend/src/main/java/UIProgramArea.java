import UIElements.UIBlock;

import java.awt.*;
import java.util.ArrayList;

public class UIProgramArea {
    private Point pos;
    private int width;
    private int height;
    private ArrayList<UIBlock> blocks = new ArrayList<>();
    private int highlightedBlockNumber = -1;

    public UIProgramArea(Point pos, int width, int height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    /**
     * Draws the backdrop and the blocks
     *
     * @param g The Graphics element
     */
    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(pos.x, pos.y, width, height);

        int i = 0;
        for (UIBlock block : blocks) {
            g.setColor(block.getColor(highlightedBlockNumber == i++));
            g.fillPolygon(block.getPolygon());
            g.drawString(block.getText(), block.getPosition().x + 10, block.getPosition().y + 10);
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

    public void increaseHighlightedBlockNumber() {
        highlightedBlockNumber++;
    }

    public void Reset() {
        highlightedBlockNumber = -1;
    }

    public boolean isWithin(int x, int y) {
        return (x > pos.x
                && x < pos.x + width
                && y > pos.y
                && y < pos.y + height);
    }

    public ArrayList<UIBlock> getBlocks() {
        return blocks;
    }
}
