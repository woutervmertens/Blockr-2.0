package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.blocks.Block;
import com.swop.uiElements.UIBlock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DisplaceBlockHandler {
    /**
     * Map with as keys all the backend blocks present in the PA and their corresponding ui block as value.
     */
    private Map<Block,UIBlock> blockUIBlockMap = new HashMap<>();

    private Map<Block, UIBlock> getBlockUIBlockMap() {
        return blockUIBlockMap;
    }

    public void putInBlockUIBlockMap(Block key, UIBlock value) {
        blockUIBlockMap.put(key, value);
    }

    public UIBlock getCorrespondingUiBlockFor(Block block) {
        try {
            return blockUIBlockMap.get(block);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public DisplaceBlockHandler(BlockrGame blockrGame) {
        this.blockrGame = blockrGame;
    }

    /**
     * Connection with backend
     */
    private BlockrGame blockrGame;

    /**
     * @pre The given position is inside the PA
     */
    public void handleReleaseInPA(int x, int y, UIBlock draggedBlock) {
        Block backendBlock = draggedBlock.getCorrespondingBlock();
        putInBlockUIBlockMap(backendBlock,draggedBlock);
        blockrGame.dropBlockInPA(backendBlock);
        draggedBlock.setPosition(blockrGame.getBlockPosition(backendBlock));
    }

    public void handleReleaseOutsidePA(int x, int y, UIBlock draggedBlock) {
        //remove the block from program area TODO: and edit the connections and gapsizes
        blockrGame.removeBlockFromPA(draggedBlock.getCorrespondingBlock());
        //remove from the map in DisplaceBlockHandler
        blockUIBlockMap.remove(draggedBlock.getCorrespondingBlock());
        // TODO: always handle the blockUIBlockMap !
    }

    public Collection<UIBlock> getAllUIBlocksInPA() {
        ArrayList<Block> backBlocks = blockrGame.getAllBlocksInPA();
        ArrayList<UIBlock> returnUIBlocks = new ArrayList<>();
        for (Block block : backBlocks){
            returnUIBlocks.add(getCorrespondingUiBlockFor(block));
        }
        return returnUIBlocks;
    }
}
