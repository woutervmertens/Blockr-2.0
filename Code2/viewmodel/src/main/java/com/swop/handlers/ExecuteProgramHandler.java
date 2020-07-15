package com.swop.handlers;

import com.swop.blocks.FunctionDefinitionBlockModel;

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
        if ((blockrGame.getProgramArea().getAllBlockModels().stream().filter(block -> !(block instanceof FunctionDefinitionBlockModel) && (block.getParentBlock() == null)).count()
                > blockrGame.getProgram().stream().filter(block -> !(block instanceof FunctionDefinitionBlockModel) && (block.getParentBlock() == null)).count())||(blockrGame.getProgram().isEmpty())) return;
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
