import uiElements.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIPalette {
    private Point pos;
    private int height = 600;
    private int width = 100;
    private boolean hidden;

    public List<UIBlock> getUiBlocks() {
        return uiBlocks;
    }

    private List<UIBlock> uiBlocks = new ArrayList<>();
    private DrawBlockHandler drawBlockHandler = new DrawBlockHandler();

    public UIPalette(Point pos, int width, int height, int blockHeight) {
        this.pos = pos;
        this.width = width;
        this.height = height;
        hidden = false;
        createUIBlocks();
    }

    /**
     * Initializes the hashmap for all the available UI blocks based on the given nb for each type.
     */
    private void createUIBlocks() {
        uiBlocks = new ArrayList<>();

        for (BlockTypes type : BlockTypes.values()) {
            uiBlocks.add(type.getNewUIBlock(0,0));
        }

        //set which buttons are available aka can be used in this level (currently all)
        for (UIBlock b : uiBlocks) {
            b.setAvailable(true);
        }
    }

    public void draw(Graphics g) {
        //Background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(pos.x, pos.y, width, height);

        if (hidden) return;

        int x = 10;
        int y = 10;
        int s = uiBlocks.size();
        int step = height / s;
        for (int i = 0; i < s; i++) {

            UIBlock uiBlock = uiBlocks.get(i);
            if (!uiBlock.isAvailable()) continue;
            uiBlock.setPosition(new Point(x, y + step * i));
            g.setColor(Color.black);
            g.drawRoundRect(5, step * i, width - 10, step, 5, 5);
            drawBlockHandler.draw(uiBlock,g,false);
        }
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

//    /**
//     * @pre x is within this palette
//     */
//    public BlockTypes getBlockTypeClicked(int x, int y) {
//        assert this.isWithin(x, y);
//
//        int s = uiBlocks.size();
//        int i = y / (height / s);
//        BlockTypes bt = null;
//        System.out.println(i);
//        if (i < s)
//            bt = uiBlocks.get(i).getType();
//        return bt;
//    }

    public int getWidth() {
        return width;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHiddenStateAs(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isWithin(int x, int y) {
        return (x > pos.x
                && x < pos.x + width
                && y > pos.y
                && y < pos.y + height);
    }
}
