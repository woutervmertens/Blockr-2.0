package blocks;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class BlockGroup {

    public BlockGroup(){
        this.blocks = new LinkedList<>();
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

    /**
     * Add a block to the end of the list
     * @param block The block to add
     */
    public void addBlockAtEnd(Block block)
    {
        blocks.add(block);
    }
}
