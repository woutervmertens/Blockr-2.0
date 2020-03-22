package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.blocks.Block;
import com.swop.uiElements.UIBlock;

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
        blockrGame.dropBlockInPA();
    }

    public void handleReleaseOutsidePA(int x, int y, UIBlock draggedBlock) {
        // TODO: remove the block from program area and edit the connections and gapsizes
        // TODO: uiPalette.setHiddenStateAs(false);
        // TODO: always handle the blockUIBlockMap !
    }
}
