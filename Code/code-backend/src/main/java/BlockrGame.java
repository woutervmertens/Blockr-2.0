import blocks.ActionBlock;
import blocks.Block;
import blocks.BlockGroup;
import blocks.StatementBlock;

public class BlockrGame {

    /**
     * Execute the given blockGroup by changing the state of the gameWorld and character step by step.
     *   Only the execution of block groups is *publicly* allowed and not of blocks !
     */
    public void executeBlockGroup(BlockGroup blockGroup) {
        // TODO
    }

    /**
     * @pre block instanceof ActionBlock || block instanceof StatementBlock
     */
    private void executeBlock(Block block) {
        assert block instanceof ActionBlock || block instanceof StatementBlock;
        // TODO: execute single block
    }
}
