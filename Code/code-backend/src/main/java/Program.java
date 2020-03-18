import blocks.*;
import worldElements.GameWorld;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A program written by the player, represented as blocks.Block elements in a (Linked) List
 */
public class Program {
    private List<Block> blocks;
    private GameWorld world;
    private Block currentBlock;
    private Block nextBlock;
    private BlockGroup blockGroup = new BlockGroup();
    private LinkedList<Block> copy;

    public Program(GameWorld world) {
        this.world = world;
    }

    public Program(GameWorld world, BlockGroup blockGroup, List<Block> blocks) {
        this.blocks = blocks;
        this.world = world;
        this.blockGroup = blockGroup;
        this.currentBlock = blockGroup.getBlocks().get(0);
    }

    public Block getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(Block nextBlock) {
        this.nextBlock = nextBlock;
    }

    public Block getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(Block currentBlock) {
        this.currentBlock = currentBlock;
    }

    public GameWorld getWorld() {
        return world;
    }

    public void reset() {
        setCopy((LinkedList<Block>) blockGroup.getBlocks().clone());
    }

    public BlockGroup getBlockGroup() {
        return blockGroup;
    }

    public void setBlockGroup(BlockGroup blockGroup) {
        this.blockGroup = blockGroup;
    }

    public LinkedList<Block> getCopy() {
        if (copy == null) copy = new LinkedList<Block>(blockGroup.getBlocks());
        return copy;
    }

    public void setCopy(LinkedList<Block> copy) {
        this.copy = copy;
    }


    public void execute() {

        try {
            setCurrentBlock(getCopy().getFirst());
        } catch (NoSuchElementException e) {
            reset();
            getWorld().reset();
        }
        if (getCopy().size() > 1) setNextBlock(getCopy().get(1));

        // condition of statementBlock is true
        if ((getCurrentBlock() instanceof StatementBlock) && (((StatementBlock) getCurrentBlock()).isConditionValid(getWorld()))) {

            //WhileBlock
            if (getCurrentBlock() instanceof WhileBlock) {
                LinkedList<Block> temp = new LinkedList<>();
                temp.addAll(((WhileBlock) getCurrentBlock()).getBody().getBlocks());
                temp.addAll(getCopy());
                setCopy(temp);
                setCurrentBlock(getCopy().getFirst());
                setNextBlock(getCopy().get(1));
            } else { //ifBlock
                getCopy().removeFirst();
                LinkedList<Block> temp = new LinkedList<>();
                temp.addAll(((IfBlock) getCurrentBlock()).getBody().getBlocks());
                temp.addAll(getCopy());
                setCopy(temp);
            }
            //CurrentBlock is not a statementBlock or condition is false
        } else {
            //ActionBlock
            if (getCurrentBlock() instanceof ActionBlock) {
                ((ActionBlock) getCurrentBlock()).execute(getWorld());
            }
            getCopy().removeFirst();
            setCurrentBlock(null);
        }

    }
}
