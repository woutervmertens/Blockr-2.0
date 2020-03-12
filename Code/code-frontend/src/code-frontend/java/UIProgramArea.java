import UIElements.BlockTypes;
import UIElements.UIBlock;

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
        for (UIBlock block : uiBlocks) {
            // TODO: call the draw function on blocks instead of having duplicate draw code for UIPArea and UIPalette
            drawBlockHandler.draw(block,g,highlightedBlockNumber == i++);
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

        for (UIBlock uiBlock: getUiBlocks()) {
            if (uiBlock.isPositionOn(x,y)) {
                return uiBlock;
            }
        }
        return null;
    }

    public ArrayList<UIBlock> getUiBlocks() {
        return uiBlocks;
    }

    public void addBlock(UIBlock block) {
        if(!uiBlocks.contains(block))
            uiBlocks.add(block);
        // TODO test & implement fit in the area
    }

    public void removeBlock(UIBlock block) {
        uiBlocks.remove(block);
    }

    public UIBlock getBlockAtPositionWithinRadius(int x, int y, int radius) {
        for (UIBlock b : getUiBlocks()) {
            // TODO
        }
        return null;
    }
}
