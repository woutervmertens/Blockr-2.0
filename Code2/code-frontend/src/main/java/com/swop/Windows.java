package com.swop;

import com.swop.blocks.Block;
import com.swop.uiElements.BlockType;
import com.swop.uiElements.BlockTypes;
import com.swop.uiElements.UIBlock;

import java.awt.*;
import java.util.Collection;

public enum Windows {
    PALETTE(new Point(0, 0), 600 / 4, 600),
    PROGRAM_AREA(new Point(PALETTE.getWidth(), 0), PALETTE.getWidth() * 2, PALETTE.getHeight()),
    GAME_WORLD(new Point(PALETTE.getWidth() + PROGRAM_AREA.getWidth(), 0), PALETTE.getWidth(), PALETTE.getHeight());

    private static BlockTypes[] types;
    private final Point position;
    private final int width;
    private final int height;
    private BlockrGame blockrGame;

    Windows(Point pos, int width, int height) {
        this.position = pos;
        this.width = width;
        this.height = height;
    }

    /**
     * Draw the palette, program area and game world of this window.
     */
    public static void drawWindows(Graphics g, boolean isPaletteHidden, Collection<UIBlock> uiBlocks, GameWorld gameWorld) {
        drawPalette(g, isPaletteHidden);
        drawProgramArea(g, uiBlocks);
        drawGameWorld(g, gameWorld);
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
        Collection<Action> gwActions = blockrGame.getGameWorldType().getSupportedActions();
        Collection<Predicate> gwPredicates = blockrGame.getGameWorldType().getSupportedPredicates();
        types = new BlockTypes[gwActions.size() + gwPredicates.size() + 3];
        int k = 0;
        //Supported actions
        for (Action a : gwActions) {
            types[k] = new BlockTypes(a.toString(), 110, BlockType.ActionType);
            types[k].setAction((Action)a);
            k++;
        }
        //Supported predicates
        for (Predicate p : gwPredicates) {
            types[k] = new BlockTypes(p.toString(), 40, BlockType.Predicate);
            types[k].setPredicate(p);
            k++;
        }
        //Blockr types
        types[k++] = new BlockTypes("Not", 40, BlockType.NotCondition);
        types[k++] = new BlockTypes("If", 110, BlockType.IfStatement);
        types[k++] = new BlockTypes("While", 110, BlockType.WhileStatement);

        int step = height / types.length;
        for (int i = 0; i < types.length; i++) {
            UIBlock uiBlock = types[i].getNewUIBlock(x, y + step * i);
            uiBlock.setPosition(new Point(x, y + step * i));
            g.setColor(Color.black);
            g.drawRoundRect(5, step * i, width - 10, step, 5, 5);
            drawBlock(uiBlock, g);
        }
    }

    private static void drawProgramArea(Graphics g, Collection<UIBlock> uiBlocks) {
        Point pos = PROGRAM_AREA.getPosition();
        int width = PROGRAM_AREA.getWidth();
        int height = PROGRAM_AREA.getHeight();

        g.setColor(Color.PINK);
        g.fillRect(pos.x, pos.y, width, height);

        for (UIBlock block : uiBlocks) {
            drawBlock(block, g);
        }
    }

    /**
     * For each grid element, draws the polygon in the elements color.
     *
     * @param g awt Graphics
     */
    private static void drawGameWorld(Graphics g, GameWorld gameWorld) {
        Point pos = GAME_WORLD.getPosition();
        gameWorld.paint(g, pos);
    }

    /**
     * Get type of the area clicked on (assuming that the click is on the palette area.
     *
     * @pre PALETTE.isWithin(x, y);
     */
    public static BlockTypes getTypeOfClick(int x, int y) {
        assert PALETTE.isWithin(x, y);

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
}
