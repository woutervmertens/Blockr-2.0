package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.windowElements.UIGameWorld;
import com.swop.blocks.ConditionBlock;
import com.swop.blocks.StatementBlock;
import com.swop.uiElements.BlockTypes;
import com.swop.uiElements.UIBlock;
import com.swop.uiElements.UIStatementBlock;
import com.swop.windowElements.UIPalette;
import com.swop.windowElements.UIProgramArea;

import java.awt.*;

/**
 * TODO: remove the blockrGame dependency and move it to com.swop.handlers package
 */
public class DisplaceBlockHandler {
    private final int radius = 10;  // Radius for connections
    private UIProgramArea uiProgramArea;
    private UIPalette uiPalette;
    private ExecuteProgramHandler executeProgramHandler;

    public DisplaceBlockHandler(UIProgramArea uiProgramArea, UIPalette uiPalette, UIGameWorld uiGameWorld, BlockrGame blockrGame) {
        this.uiProgramArea = uiProgramArea;
        this.uiPalette = uiPalette;

        executeProgramHandler = new ExecuteProgramHandler(uiProgramArea, uiGameWorld, blockrGame);
    }

    /**
     * @param draggedBlock The block that was dragged until release (if any)
     * TODO: handle previous and next !!!!!!!!
     */
    public void handleRelease(int x, int y, UIBlock draggedBlock) {
        if (draggedBlock == null) throw new IllegalArgumentException("No block was dragged !");

        UIBlock closeBlock = null;
        Point connectionPoint = null;

        if (uiProgramArea.isWithin(x, y)) {
            Point dropPos = new Point(x, y);

            int type = 0;

            if ((draggedBlock.getType() != BlockTypes.NotCondition &&
                 draggedBlock.getType() != BlockTypes.WallInFrontCondition)) {
                // 1) plug
                closeBlock = uiProgramArea.getBlockWithPlugForBlockWithinRadius(draggedBlock, radius);
                type = 1;
                if (closeBlock == null) {
                    // 2) socket
                    closeBlock = uiProgramArea.getBlockWithSocketForBlockWithinRadius(draggedBlock, radius);
                    type = 2;
                    if (closeBlock == null) {
                        // 3) statement body
                        closeBlock = uiProgramArea.getStatementBlockBodyPlugWithinRadius(draggedBlock,radius);
                        type = 3;
                    }
                }
            } else {
                // 4) statement condition
                closeBlock = uiProgramArea.getStatementBlockConditionPlugWithinRadius(draggedBlock,radius);
                type = 4;
            }

            if (closeBlock != null) {
                switch (type) {
                    case 1: //Plug
                        draggedBlock.setParentStatement(closeBlock.getParentStatement());
                    case 2: //Socket
                        connectionPoint = uiProgramArea.getConnectionPoint(draggedBlock, closeBlock);
                        executeProgramHandler.addBlockToProgramArea(draggedBlock, closeBlock);
                        break;
                    case 3: //Statement body
                        connectionPoint = ((UIStatementBlock)closeBlock).getBodyPlugPosition(uiProgramArea);
                        addBlockToBody(draggedBlock,closeBlock);
                        break;
                    case 4: //Statement condition
                        connectionPoint = ((UIStatementBlock)closeBlock).getConditionPlugPosition(uiProgramArea);
                        addBlockToConditions(draggedBlock,closeBlock);
                        break;
                }
                System.out.println("Close block: " + closeBlock.getText());
                draggedBlock.setPosition(connectionPoint);
                if(draggedBlock.getParentStatement() != null){
                    ((UIStatementBlock)draggedBlock.getParentStatement()).increaseGapSize(draggedBlock.getHeight() + 5);
                }
            } else {
                //TODO: create new program in backend
                draggedBlock.setPosition(dropPos);
                //Backend add block to current blockgroup
                executeProgramHandler.addBlockToProgramArea(draggedBlock, null);
            }
            uiProgramArea.addBlock(draggedBlock);

            executeProgramHandler.reset();


        } else {
            uiProgramArea.removeBlock(draggedBlock);
            if(draggedBlock.getParentStatement() != null) {
                ((UIStatementBlock)draggedBlock.getParentStatement()).decreaseGapSize(draggedBlock.getHeight() + 5);
                ((StatementBlock)draggedBlock.getParentStatement().getBlock()).getBody().getBlocks().remove(draggedBlock.getBlock());
            }
            uiPalette.setHiddenStateAs(false);
        }
    }

    /**
     * Add the new block to the conditions of the closest Statement block
     *
     * @param draggedBlock the new block
     * @param b            the statement block
     */
    private void addBlockToConditions(UIBlock draggedBlock, UIBlock b) {
        ((StatementBlock) b.getBlock()).addCondition((ConditionBlock) draggedBlock.getBlock());
    }

    /**
     * Add the new block to the body of the closest Statement block
     *
     * @param draggedBlock the new block
     * @param b            the statement block
     */
    private void addBlockToBody(UIBlock draggedBlock, UIBlock b) {
        ((StatementBlock) b.getBlock()).getBody().addBlockAtEnd(draggedBlock.getBlock());
        draggedBlock.setParentStatement(b);
    }
}
