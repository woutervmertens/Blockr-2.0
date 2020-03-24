package com.swop;

import com.swop.uiElements.BlockTypes;
import com.swop.uiElements.UIBlock;

import java.awt.*;

public enum Windows {
    PALETTE(new Point(0, 0), 600 / 4, 600),
    PROGRAM_AREA(new Point(PALETTE.getWidth(), 0), PALETTE.getWidth() * 2, PALETTE.getHeight()),
    GAME_WORLD(new Point(PALETTE.getWidth() + PROGRAM_AREA.getWidth(), 0), PALETTE.getWidth(), PALETTE.getHeight());

    private Point position;
    private int width;
    private int height;

    Windows(Point pos, int width, int height) {
        this.position = pos;
        this.width = width;
        this.height = height;
    }

    public Point getPosition() {
        return position;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    /**
     * Check whether the pos represented by the given x and y is within this window.
     */
    public boolean isWithin(int x, int y) {
        return (x > getPosition().x
                && x < getPosition().x + getWidth()
                && y > getPosition().y
                && y < getPosition().y + getHeight());
    }

    /**
     * Draw the palette, program area and game world of this window.
     */
    public static void drawWindows(Graphics g, boolean isPaletteHidden) {
        drawPalette(g, isPaletteHidden);
        drawProgramArea(g);
        drawGameWorld(g);
    }

    private static void drawBlock(UIBlock block, Graphics g) {
        g.setColor(block.getColor());
        g.fillPolygon(block.getPolygon());
        g.setColor(Color.BLACK);
        g.drawString(block.getText(), block.getTextPosition().x, block.getTextPosition().y);
    }

    private static void drawPalette(Graphics g, boolean isHidden) {
        Point pos = PALETTE.getPosition();
        int width = PALETTE.getWidth();
        int height = PALETTE.getHeight();

        //Background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(pos.x, pos.y, width, height);

        if (isHidden) return;

        int x = 15;
        int y = 10;
        BlockTypes[] types = BlockTypes.values();
        int step = height / types.length;
        for (int i = 0; i < types.length; i++) {
            UIBlock uiBlock = types[i].getNewUIBlock(x, y + step * i);
            // TODO: if (!uiBlock.isAvailable()) continue;
            uiBlock.setPosition(new Point(x, y + step * i));
            g.setColor(Color.black);
            g.drawRoundRect(5, step * i, width - 10, step, 5, 5);
            drawBlock(uiBlock, g);
        }
    }

    private static void drawProgramArea(Graphics g) {
        Point pos = PROGRAM_AREA.getPosition();
        int width = PROGRAM_AREA.getWidth();
        int height = PROGRAM_AREA.getHeight();

        g.setColor(Color.PINK);
        g.fillRect(pos.x, pos.y, width, height);

//        TODO: draw all blocks in PA (find a way)
//        int i = 0;
//        for (UIBlock block : uiBlocks) {
//            drawBlock(block, g, highlightedBlockNumber == i++);
//        }
    }

    /**
     * For each grid element, draws the polygon in the elements color.
     *
     * @param g awt Graphics
     */
    private static void drawGameWorld(Graphics g) {
//        TODO
//        getUiCharacter();
//        if (grid == null) return;
//        for (UIGridElement[] elCol : grid) {
//            for (UIGridElement el : elCol) {
//                g.setColor(el.getColor());
//                g.drawPolygon(el.getPolygon(elementSize, pos));
//            }
//        }
//        g.setColor(uiCharacter.getColor());
//        g.drawPolygon(uiCharacter.getPolygon(elementSize, pos));
    }

    /**
     * Get type of the area clicked on (assuming that the click is on the palette area.
     * @pre PALETTE.isWithin(x, y);
     */
    public static BlockTypes getTypeOfClick(int x, int y) {
        assert PALETTE.isWithin(x, y);

        BlockTypes[] types = BlockTypes.values();
        int s = types.length;
        int i = y / (PALETTE.getHeight() / s);
        BlockTypes bt = null;
        System.out.println(i);
        if (i < s)
            bt = types[i];
        return bt;

//        BlockTypes[] types = BlockTypes.values();
//        int step = height / types.length;
//
//        int i = Math.floorDiv(PALETTE.getHeight(), y);
    }
}
