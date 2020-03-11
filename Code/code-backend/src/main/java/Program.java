import blocks.Block;

/**
 * A program written by the player, represented as blocks.Block elements in a (Linked) List
 */
public class Program {

    public Program() { }

    public Program(BlockGroup blockGroup) {
        setBlockGroup(blockGroup);
    }

    private Block currentBlock;

    public Block getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(Block currentBlock) {
        this.currentBlock = currentBlock;
    }

    private BlockGroup blockGroup;

    public BlockGroup getBlockGroup() {
        return blockGroup;
    }

    public void setBlockGroup(BlockGroup blockGroup) { this.blockGroup = blockGroup; }

    /**
     * Runs program block:
     * - ActionBlock: parse result to gameworld
     * - StatementBlock: check conditions, if true: add children to queue
     */
    public void executeNextStep() {
        if (getCurrentBlock() == null && ! getBlockGroup().getBlocks().isEmpty())
            currentBlock = getBlockGroup().getBlocks().get(0);

        // TODO: depending on the next block execute it
    }

    public void execute() {
        // TODO
    }
}
