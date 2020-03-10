import blocks.Block;

import java.util.ArrayList;
import java.util.List;

public class Palette {
    int maxBlocks;
    List<Block> allBlocks;

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
