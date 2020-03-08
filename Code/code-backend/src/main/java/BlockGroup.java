import Blocks.Block;
import Blocks.StatementBlock;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class BlockGroup {
    Point position;
    ArrayList<Block> blocks;

    public BlockGroup(Point position, ArrayList<Block> blocks) {
        this.position = position;
        this.blocks = blocks;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public Block getBlock(int id)
    {
        return blocks.get(id);
    }

    /**
     * Adds children of statementBlock to end of program queue, if statement is a loop: current block gets added to end again
     * @param id The ID of the currentBlock in the queue
     */
    public void addChildrenToEnd(int id)
    {
        StatementBlock sb = ((StatementBlock)blocks.get(id));
        blocks.addAll((Collection<? extends Block>) sb.getChildren());
        if(sb.isLoop())
        {
            blocks.add(sb);
        }
    }
}
