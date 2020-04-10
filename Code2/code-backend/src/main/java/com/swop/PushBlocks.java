package com.swop;

import com.swop.blocks.Block;
import com.swop.blocks.StatementBlock;

import java.awt.*;
import java.util.List;

public interface PushBlocks {
    /**
     * Push down or up (based on the sign of the given distance) all blocks of the given list starting from the given index.
     */
    static void pushBlocksInListFromIndexWithDistance(List<Block> blockList, int index, int distance) {
        for (int i = index; i < blockList.size(); i++) {
            Block currentBlock = blockList.get(i);
            currentBlock.setPosition(new Point(currentBlock.getPosition().x, currentBlock.getPosition().y + distance));
            if (currentBlock instanceof StatementBlock) {
                for (Block currentBodyBlock : ((StatementBlock) currentBlock).getBodyBlocks()) {
                    currentBodyBlock.setPosition(new Point(currentBodyBlock.getPosition().x, currentBodyBlock.getPosition().y + distance));
                }
            }
        }
    }
}
