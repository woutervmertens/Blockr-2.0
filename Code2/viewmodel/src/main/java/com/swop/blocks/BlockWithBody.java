package com.swop.blocks;

import java.awt.*;

public abstract class BlockWithBody extends Block{
    BlockModelWithBody model;

    public BlockWithBody(BlockModelWithBody model) {
        super(model);
    }

    public void setPosition(Point position) {
        try {
            int dx = position.x - model.getPosition().x;
            int dy = position.y - model.getPosition().y;
            model.setPosition(position);
            for (BlockModel bodyBlockModel : model.getBodyBlockModels()) {
                bodyBlockModel.setPosition(new Point(bodyBlockModel.getPosition().x + dx, bodyBlockModel.getPosition().y + dy));
            }
        } catch (NullPointerException e) {
            model.setPosition(position);
        }
    }

    /**
     * 1) Add the given block after the given existing block
     * 2) And push all others inside the body
     * 3) And make all the parents' gap sizes bigger.
     * <p>
     * If existing block is null add the given block at the start of the body
     *
     * @param blockModel The block to add.
     * @param existingBlockModel The already existing block.
     */
    public void addBodyBlockAfter(BlockModel blockModel, BlockModel existingBlockModel) {
        if (existingBlockModel == null) throw new IllegalArgumentException();
        if (!model.bodyBlockModels.contains(existingBlockModel)) throw new IllegalArgumentException();
        insertBodyBlockAtIndex(blockModel, model.bodyBlockModels.indexOf(existingBlockModel) + 1);

    }

    /**
     * Add the given block before the given existing block.
     *
     * @param blockModel The block to add.
     * @param existingBlockModel The already existing block.
     */
    public void addBodyBlockBefore(BlockModel blockModel, BlockModel existingBlockModel) {
        if (existingBlockModel == null) throw new IllegalArgumentException();
        if (!model.bodyBlockModels.contains(existingBlockModel)) throw new IllegalArgumentException();
        insertBodyBlockAtIndex(blockModel, model.bodyBlockModels.indexOf(existingBlockModel));
    }

    /**
     * Add the given block at a certain index in the body.
     *
     * @param block The block to add.
     * @param index The index.
     */
    public void insertBodyBlockAtIndex(BlockModel block, int index) {
        // 1) Add to the body blocks of this block


        // 2) Push all next body blocks down


        // 3) If this is a statement, increase the gap and all eventual superior parent statements
    }
}
