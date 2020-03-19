package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.windowElements.UIGameWorld;
import com.swop.uiElements.UIBlock;
import com.swop.uiElements.UICharacter;
import com.swop.windowElements.UIProgramArea;

/**
 * TODO: remove the blockrGame dependency and move it to com.swop.handlers package
 */
public class ExecuteProgramHandler {
    private UIProgramArea uiProgramArea;
    private UIGameWorld uiGameWorld;
    private UICharacter uiCharacter;
    private BlockrGame blockrGame;
    private BackendConverter converter;

    public ExecuteProgramHandler(UIProgramArea uiProgramArea, UIGameWorld uiGameWorld, BlockrGame blockrGame) {
        this.uiProgramArea = uiProgramArea;
        this.uiGameWorld = uiGameWorld;
        this.blockrGame = blockrGame;
        converter = new BackendConverter();
    }

    public void execute() {
        uiProgramArea.highlightNextBlock();
        blockrGame.executeBlock();
        // TODO: fix bug of re-executing directly
        // TODO: fix bug of not moving forward twice
    }

    public void reset() {
        blockrGame.reset();
        uiGameWorld.setGrid(converter.convertGrid(blockrGame.getGrid(),blockrGame.getGoalPosition()));
        uiProgramArea.restartProgram();
    }

    /**
     * Add the given (dragged) block to the program area.
     *
     * @param block The dragged block
     * @param previousBlock The (eventual) previous block to which the given dragged block should be linked.
     *                      Can be null !
     */
    public void addBlockToProgramArea(UIBlock block, UIBlock previousBlock) {
        blockrGame.addBlockToBlockGroup(block.getBlock());
        block.setPrevious(previousBlock);
        // TODO: linked list
    }

    public void createNewBlockGroup(UIBlock block) {
        blockrGame.createNewBlockGroup(block.getBlock());
    }

}
