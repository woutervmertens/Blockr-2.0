import blocks.ActionBlock;
import blocks.Block;
import blocks.StatementBlock;

import java.util.ArrayList;

/**
 * A program written by the player, represented as Block elements in a List
 */
public class Program {
    private int currentBlockId;
    private BlockGroup blockQueue;
    private ArrayList<Block> blockQueueBackup;

    public Program(int currentBlockId, BlockGroup blockQueue) {
        this.currentBlockId = currentBlockId;
        setBlockQueue(blockQueue);
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
        this.blockQueueBackup = this.blockQueue.getBlocks();
    }

    /**
     * Resets active block to start and reverts to first blockQueue
     */
    public void Reset() {
        currentBlockId = 0;
        blockQueue.setBlocks(blockQueueBackup);
    }

    /**
     * Runs program block:
     * - ActionBlock: parse result to gameworld
     * - StatementBlock: check conditions, if true: add children to queue
     */
    public void executeNextStep() {
        currentBlockId++;
        Block cb = blockQueue.getBlock(currentBlockId);
        if (cb instanceof ActionBlock) {
            //TODO parse current block: GameWorld.parse(cb);
        } else if (cb instanceof StatementBlock) {
            //TODO check conditions: if(GameWorld.checkConditions(cb.getConditions()))
            {
                addChildrenToQueue();
            }
        }
        //conditionBlock not allowed
    }

    /**
     * Calls BlockGroup to do the following:
     * Adds children of statementBlock to end of program queue, if statement is a loop: current block gets added to end again
     */
    public void addChildrenToQueue() {
        blockQueue.addChildrenBehindParent(currentBlockId);
    }
}
