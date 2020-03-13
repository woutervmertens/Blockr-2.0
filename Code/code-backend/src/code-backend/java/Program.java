import blocks.*;
import worldElements.GameWorld;

import java.util.List;

/**
 * A program written by the player, represented as blocks.Block elements in a (Linked) List
 */
public class Program {

    public Program(GameWorld world) {
        this.world = world;

    }
    private GameWorld world;

    public Program(GameWorld world, BlockGroup blockGroup) {
        this.world = world;
        this.blockGroup = blockGroup;
        this.currentBlock = blockGroup.getBlocks().get(0);
    }

    private Block currentBlock;

    public Block getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(Block currentBlock) {
        this.currentBlock = currentBlock;
    }

    public GameWorld getWorld() {
        return world;
    }

    private BlockGroup blockGroup;

    public BlockGroup getBlockGroup() {
        return blockGroup;
    }

    public void setBlockGroup(BlockGroup blockGroup) { this.blockGroup = blockGroup; }


    public void execute() {
        blockGroup.getBlocks().forEach(block -> block.execute(world));

    }
}
