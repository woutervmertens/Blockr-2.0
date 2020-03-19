package com.swop.windowElements;

import com.swop.handlers.DrawBlockHandler;
import com.swop.uiElements.HorizontallyConnectable;
import com.swop.uiElements.UIBlock;
import com.swop.uiElements.VerticallyConnectable;

import java.awt.*;
import java.util.ArrayList;

public class UIProgramArea {
    private final Point pos;
    private final int width;
    private final int height;
    private DrawBlockHandler drawBlockHandler = new DrawBlockHandler();
    private ArrayList<UIBlock> uiBlocks = new ArrayList<>();
    private int highlightedBlockNumber = -1;

    public UIProgramArea(Point pos, int width, int height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    /**
     * Get the distance between two given points.
     *
     * @param b Point1
     * @param p Point2
     */
    private static int getDistance(Point b, Point p) {
        return (int) Math.sqrt((p.getX() - b.getX()) * (p.getX() - b.getX()) + (p.getY() - b.getY()) * (p.getY() - b.getY()));
    }

    public Point getPos() {
        return this.pos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Draws the backdrop and the com.swop.blocks
     *
     * @param g The Graphics element
     */
    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(pos.x, pos.y, width, height);

        int i = 0;
        for (UIBlock block : uiBlocks) {
            drawBlockHandler.draw(block, g, highlightedBlockNumber == i++);
        }
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
     * Adds UIBlock to list of com.swop.blocks if not already in list
     *
     * @param block UIBlock to add
     */
    public void addBlock(UIBlock block) {
        if (!uiBlocks.contains(block)) {
            uiBlocks.add(block);
        }
        // TODO test & implement fit in the area
    }

    /**
     * Removes UIBlock from list of com.swop.blocks
     *
     * @param block UIBlock to remove
     */
    public void removeBlock(UIBlock block) {
        uiBlocks.remove(block);
    }

    public UIBlock getBlockWithPlugForBlockWithinRadius(UIBlock uiBlock, int radius) {
        for (UIBlock b : getUiBlocks()) {
            if (b == uiBlock || (uiBlock instanceof HorizontallyConnectable && !(b instanceof HorizontallyConnectable))
                    || (uiBlock instanceof VerticallyConnectable && !(b instanceof VerticallyConnectable)))
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
                    || (uiBlock instanceof VerticallyConnectable && !(b instanceof VerticallyConnectable)))
                continue;

            // TODO: maybe type cast with interfaces
            if (getDistance(uiBlock.getPlugPosition(), b.getSocketPosition()) <= radius) {
                return b;
            }

        }
        return null;
    }

    public int getNumBlocks() {
        return uiBlocks.size();
    }

    public void highlightNextBlock() {
        if (highlightedBlockNumber >= getNumBlocks()) highlightedBlockNumber = 0;
        else highlightedBlockNumber++;
    }

    public void restartProgram() {
        highlightedBlockNumber = -1;
    }
}
