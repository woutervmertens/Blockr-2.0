package com.swop;

import com.swop.blocks.Block;

import java.awt.*;
import java.util.List;

public interface PushBlocks {
    /**
     * Push down or up (based on the sign of the given distance) all blocks of the given list starting from the given index.
     */
    private static void pushBlocksInListFromIndexWithDistance(List<Block> blockList, int index, int distance) {
        if (index < 0) return;
        // Push all the body blocks starting from the index
        for (int i = index; i < blockList.size(); i++) {
            Block currentBlock = blockList.get(i);
            currentBlock.setPosition(new Point(currentBlock.getPosition().x, currentBlock.getPosition().y + distance));
        }
    }

    /**
     * Push (up or down) the blocks in this list and all eventual body blocks of superior parents.
     * Whenever calling this method you should make sure also the program is pushed.
     *
     * @param blockList List of blocks that should be pushed
     * @param index     Index from which the blocks should be pushed
     * @param distance  Distance by which the blocks should be pushed (up: positive, down: negative)
     */
    static void pushFrom(List<Block> blockList, int index, int distance) {
        if (blockList.isEmpty()) return;
        // 1) Push blocks in same list
        pushBlocksInListFromIndexWithDistance(blockList, index, distance);
        // 2) Push blocks of eventual superior parents
        Block currentParent = blockList.get(0).getParentBlock();
        if (currentParent != null) {
            while (currentParent.getParentBlock() != null) {
                PushBlocks.pushBlocksInListFromIndexWithDistance(currentParent.getParentBlock().getBodyBlocks(),
                        currentParent.getParentBlock().getBodyBlocks().indexOf(currentParent) + 1, distance);
                currentParent = currentParent.getParentBlock();
            }
        }
    }
}
