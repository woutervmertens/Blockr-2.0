package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.blocks.Block;
import com.swop.blocks.FunctionDefinitionBlock;

public class ExecuteProgramHandler {
    private final SharedData sharedData;

    public ExecuteProgramHandler(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    /**
     * Executes the next block in the program.
     */
    public void executeNext() {
        BlockrGame blockrGame = sharedData.getBlockrGame();
        // TODO: subtract from getNumBlocks the blocks in a functiondef
        if ((blockrGame.getProgramArea().getAllBlocks().stream().filter(block -> !(block instanceof FunctionDefinitionBlock) && (block.getParentBlock() == null)).count()
                > blockrGame.getProgram().stream().filter(block -> !(block instanceof FunctionDefinitionBlock) && (block.getParentBlock() == null)).count())||(blockrGame.getProgram().isEmpty())) return;
        blockrGame.executeNext();
        updateHighlight();
    }

    /**
     * Updates the blocks that need to be highlighted.
     */
    public void updateHighlight() {
        BlockrGame blockrGame = sharedData.getBlockrGame();
        sharedData.setHighlightedBlock(sharedData.getCorrespondingUiBlockFor(blockrGame.getToHighlightBlock()));
    }

}
