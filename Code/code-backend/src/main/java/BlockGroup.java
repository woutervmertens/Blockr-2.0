import blocks.Block;

import java.awt.*;
import java.util.List;
import java.util.LinkedList;

public class BlockGroup {
    public BlockGroup(Point position, List<Block> blocks) {
        this.position = position;
        this.blocks = new LinkedList<>(blocks);
    }

    /**
     * The position of this BlockGroup in the canvas.
     */
    private Point position;

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * The blocks who represent the linked list.
     */
    private List<Block> blocks;

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = new LinkedList<>(blocks);
    }

    /**
     * Add the given BlockGroup behind/after the given block.
     *
     * @param group The to be added block group
     * @param block The block of this block group to queue the given group behind
     * @pre The given block is part of this BlockGroup
     * @post If given block is last block of group, then simply add given group to queue
     * @post Otherwise If given block is in the middle of this BlockGroup, then insert given group
     */
    public void addGroupBehindBlock(BlockGroup group, Block block) {
        assert getBlocks().contains(block);

        int i = getBlocks().indexOf(block);
        getBlocks().addAll(i + 1, group.getBlocks());
    }
}
