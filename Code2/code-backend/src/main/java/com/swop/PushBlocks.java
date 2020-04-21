package com.swop;

import com.swop.blocks.Block;
import com.swop.blocks.StatementBlock;

import java.awt.*;
import java.util.List;

// TODO: remove dependency of StatementBlock with PushBlocks and handle only in ProgramArea
public interface PushBlocks {
    /**
     * Push down or up (based on the sign of the given distance) all blocks of the given list starting from the given index.
     */
    static void pushBlocksInListFromIndexWithDistance(List<Block> blockList, int index, int distance) {
        if (index < 0) return;
        // Push all the body blocks starting from the index
        for (int i = index; i < blockList.size(); i++) {
            Block currentBlock = blockList.get(i);
            currentBlock.setPosition(new Point(currentBlock.getPosition().x, currentBlock.getPosition().y + distance));
//            if (currentBlock instanceof StatementBlock) {
//                for (Block currentBodyBlock : ((StatementBlock) currentBlock).getBodyBlocks()) {
//                    currentBodyBlock.setPosition(new Point(currentBodyBlock.getPosition().x, currentBodyBlock.getPosition().y + distance));
//                }
//            }
        }
    }

    /**
     * todo
     * @param bodyBlocks list of blocks of the body
     * @param distance todo
     */
    static void pushBodyBlocksOfSuperiorParents(List<Block> bodyBlocks, int distance) {
        assert !bodyBlocks.isEmpty();
        assert bodyBlocks.get(0).getParentStatement() != null;

        Block currentParent = bodyBlocks.get(0).getParentStatement();
        while (currentParent.getParentStatement() != null) {
            PushBlocks.pushBlocksInListFromIndexWithDistance(currentParent.getParentStatement().getBodyBlocks(),
                    currentParent.getParentStatement().getBodyBlocks().indexOf(currentParent) + 1, distance);
            currentParent = currentParent.getParentStatement();
        }
    }
}
