import UIElements.UIBlock;

import java.awt.*;
import java.util.ArrayList;

public class UIProgramArea {
    public Point getPos() {
        return this.pos;
    }

    private final Point pos;

    public int getWidth() {
        return width;
    }

    private final int width;

    public int getHeight() {
        return height;
    }

    private final int height;
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
            // TODO: call the draw function on blocks instead of having duplicate draw code for UIPArea and UIPalette
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

    public void addBlock(UIBlock block) {
        blocks.add(block);
        // TODO test & implement fit in the area
    }
}
