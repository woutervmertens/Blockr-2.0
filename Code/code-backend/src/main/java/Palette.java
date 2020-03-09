import Blocks.Block;

import java.util.ArrayList;

public class Palette {
    int maxBlocks;
    ArrayList<Block> allBlocks;

    public void hideBlocks(){
        setBlocksAvailable(false);
        //TODO repaint
    }
    public void revealBlocks()
    {
        setBlocksAvailable(true);
        //TODO repaint
    }

    private void setBlocksAvailable(boolean tf){
        for (Block b : allBlocks) {
            b.setAvailable(tf);
        }
    }
}
