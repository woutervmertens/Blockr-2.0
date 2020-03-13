import UIElements.HorizontallyConnectable;
import UIElements.UIBlock;
import UIElements.VerticallyConnectable;

import java.awt.*;
import java.util.ArrayList;

public class UIProgramArea {
    private DrawBlockHandler drawBlockHandler = new DrawBlockHandler();

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
    private ArrayList<UIBlock> uiBlocks = new ArrayList<>();
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

    /**
     * @pre x is within this program area
     */
    public UIBlock getUiBlockClicked(int x, int y) {
        assert this.isWithin(x, y);

        for (UIBlock uiBlock : getUiBlocks()) {
            if (uiBlock.isPositionOn(x, y)) {
                return uiBlock;
            }
        }
        return null;
    }

    public ArrayList<UIBlock> getUiBlocks() {
        return uiBlocks;
    }

    /**
     * Adds UIBlock to list of blocks if not already in list
     * @param block UIBlock to add
     */
    public void addBlock(UIBlock block) {
        if (!uiBlocks.contains(block)) {
            uiBlocks.add(block);
        }
        // TODO test & implement fit in the area
    }

    /**
     * Removes UIBlock from list of blocks
     * @param block UIBlock to remove
     */
    public void removeBlock(UIBlock block) {
        uiBlocks.remove(block);
    }

    public UIBlock getBlockWithPlugForBlockWithinRadius(UIBlock uiBlock, int radius) {
        for (UIBlock b : getUiBlocks()) {
            if (b == uiBlock || (uiBlock instanceof HorizontallyConnectable && !(b instanceof HorizontallyConnectable))
                    || (uiBlock instanceof VerticallyConnectable   && !(b instanceof VerticallyConnectable)))
                continue;

            // TODO: maybe type cast with interfaces
            if (getDistance(uiBlock.getSocketPosition(), b.getPlugPosition()) <= radius) {
                return b;
            }

        }
        return null;
    }

    public UIBlock getBlockWithSocketForBlockWithinRadius(UIBlock uiBlock, int radius) {
        for (UIBlock b : getUiBlocks()) {
            if (b == uiBlock || (uiBlock instanceof HorizontallyConnectable && !(b instanceof HorizontallyConnectable))
                             || (uiBlock instanceof VerticallyConnectable   && !(b instanceof VerticallyConnectable)))
                continue;

            // TODO: maybe type cast with interfaces
            if (getDistance(uiBlock.getPlugPosition(), b.getSocketPosition()) <= radius) {
                return b;
            }

        }
        return null;
    }

    /**
     * Get the distance between two given points.
     * @param b Point1
     * @param p Point2
     */
    private static int getDistance(Point b, Point p) {
        return (int) Math.sqrt((p.getX() - b.getX()) * (p.getX() - b.getX()) + (p.getY() - b.getY()) * (p.getY() - b.getY()));
    }

    public int getNumBlocks() {
        return uiBlocks.size();
    }
}
