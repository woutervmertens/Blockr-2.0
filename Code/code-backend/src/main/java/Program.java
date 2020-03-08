/**
 * A program written by the player, represented as Block elements in a List
 */
public class Program {
    int currentBlockId;
    BlockGroup blockQueue;

    public Program(int currentBlockId, BlockGroup blockQueue) {
        this.currentBlockId = currentBlockId;
        this.blockQueue = blockQueue;
    }

    public int getCurrentBlockId() {
        return currentBlockId;
    }

    public void setCurrentBlockId(int currentBlockId) {
        this.currentBlockId = currentBlockId;
    }

    public BlockGroup getBlockQueue() {
        return blockQueue;
    }

    public void setBlockQueue(BlockGroup blockQueue) {
        this.blockQueue = blockQueue;
    }

    /**
     * Runs program block
     */
    public void executeNextStep(){
        currentBlockId++;
        //TODO parse current block
        //TODO check conditions if needed
        //TODO call changes to gameworld if needed
    }

    /**
     * Calls BlockGroup to do the following:
     * Adds children of statementBlock to end of program queue, if statement is a loop: current block gets added to end again
     */
    public void addChildrenToQueue(){
        blockQueue.addChildrenToEnd(currentBlockId);
    }
}
