import blocks.*;
import worldElements.GameWorld;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public Block getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(Block nextBlock) {
        this.nextBlock = nextBlock;
    }

    private Block nextBlock;

    public Block getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(Block currentBlock) {
        this.currentBlock = currentBlock;
    }

    public GameWorld getWorld() {
        return world;
    }

    public void reset()
    {
        setCopy(blockGroup.getBlocks());
    }

    private BlockGroup blockGroup;

    public BlockGroup getBlockGroup() {
        return blockGroup;
    }

    public void setBlockGroup(BlockGroup blockGroup) { this.blockGroup = blockGroup; }

    public LinkedList<Block> getCopy() {
        return copy;
    }

    public void setCopy(LinkedList<Block> copy) {
        this.copy = copy;
    }

    private LinkedList<Block> copy = new LinkedList<Block>(blockGroup.getBlocks());


    public void execute() {
        setCurrentBlock(getCopy().getFirst());
        setNextBlock(getCopy().get(1));
        if ((getCurrentBlock() instanceof StatementBlock) && (((StatementBlock) getCurrentBlock()).isConditionValid(getWorld())) ){

            if (getCurrentBlock() instanceof WhileBlock){
                LinkedList<Block> temp = new LinkedList<>();
                temp.addAll(((WhileBlock) getCurrentBlock()).getBody().getBlocks());
                temp.addAll(getCopy());
                setCopy(temp);
                setCurrentBlock(getCopy().getFirst());
                setNextBlock(getCopy().get(1));
            }

            else{ //ifBlock
                getCopy().removeFirst();
                LinkedList<Block> temp = new LinkedList<>();
                temp.addAll(((IfBlock) getCurrentBlock()).getBody().getBlocks());
                temp.addAll(getCopy());
                setCopy(temp);
            }
        } else{
            if (getCurrentBlock() instanceof ActionBlock){
                ((ActionBlock) getCurrentBlock()).execute(getWorld());
            }
            getCopy().removeFirst();
            setCurrentBlock(null);
        }

    }
}
