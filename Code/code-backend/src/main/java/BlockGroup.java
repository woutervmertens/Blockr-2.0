import blocks.Block;
import blocks.StatementBlock;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class BlockGroup {
    private Point position;
    private ArrayList<Block> blocks;

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

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public Block getBlock(int id)
    {
        return blocks.get(id);
    }

    /**
     * Adds children of statementBlock behind the parent in the program queue, if statement is a loop: current block gets added to end again
     * @param id The ID of the currentBlock in the queue
     */
    public void addChildrenBehindParent(int id)
    {
        StatementBlock sb = ((StatementBlock)blocks.get(id));
        blocks.addAll(id + 1,(Collection<? extends Block>) sb.getChildren());
        if(sb.isLoop())
        {
            blocks.add(id + ((Collection<? extends Block>) sb.getChildren()).size() + 1,sb);
        }
    }
}
