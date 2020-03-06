package Blocks;

import java.awt.*;
import java.util.LinkedList;

public class BlockGroup {
    Point pos;
    LinkedList<Block> blocks;

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public LinkedList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(LinkedList<Block> blocks) {
        this.blocks = blocks;
    }
}
