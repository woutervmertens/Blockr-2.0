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

        //TODO: create block where needed
        if (uiProgramArea.isWithin(x, y)) {
            Point dropPos = new Point(x, y);


            if ((draggedBlock.getType() != BlockTypes.NotCondition ||
                 draggedBlock.getType() != BlockTypes.WallInFrontCondition)) {
                // 1) plug
                closeBlock = uiProgramArea.getBlockWithPlugForBlockWithinRadius(draggedBlock, radius);
                if (closeBlock == null) {
                    // 2) socket
                    closeBlock = uiProgramArea.getBlockWithSocketForBlockWithinRadius(draggedBlock, radius);
                    if (closeBlock == null) {
                        // 3) statement body
                        closeBlock = uiProgramArea.getStatementBlockBodyPlugWithinRadius(draggedBlock,radius);
                    }
                }
            } else {
                closeBlock = uiProgramArea.getStatementBlockConditionPlugWithinRadius(draggedBlock,radius);
            }

            if (closeBlock != null) {
                connectionPoint = uiProgramArea.getConnectionPoint(draggedBlock, closeBlock);
                // TODO: add block to program correctly
            }


            // TODO: remove all the rest

            // 1) EVENTUAL PLUG
            closeBlock = uiProgramArea.getBlockWithPlugForBlockWithinRadius(draggedBlock, radius);
            connectionPoint = (closeBlock != null) ?
                    closeBlock.getPlugPosition()
                    : null;
            if(closeBlock != null) {
                executeProgramHandler.addBlockToProgramArea(draggedBlock, closeBlock);
                draggedBlock.setParentStatement(closeBlock.getParentStatement());
            }

            // 2) EVENTUAL SOCKET
            if (closeBlock == null) {
                closeBlock = uiProgramArea.getBlockWithSocketForBlockWithinRadius(draggedBlock, radius);
                connectionPoint = (closeBlock != null) ?
                        new Point(closeBlock.getSocketPosition().x, closeBlock.getSocketPosition().y - draggedBlock.getHeight() - 10)
                        : null;
                if(closeBlock != null) executeProgramHandler.addBlockToProgramArea(draggedBlock, closeBlock.getPrevious());
            }

            // 3) EVENTUAL CONDITION TO STATEMENT
            if (closeBlock == null && (draggedBlock.getType() == BlockTypes.NotCondition ||
                    draggedBlock.getType() == BlockTypes.WallInFrontCondition)) {

                closeBlock = uiProgramArea.getStatementBlockConditionPlugWithinRadius(draggedBlock,radius);
                connectionPoint = (closeBlock != null) ?
                        ((UIStatementBlock)closeBlock).getConditionPlugPosition(uiProgramArea)
                        : null;
                if(closeBlock != null) addBlockToConditions(draggedBlock,closeBlock);

            }

            // 4) EVENTUAL BODY TO STATEMENT
            if (closeBlock == null && (draggedBlock.getType() != BlockTypes.NotCondition ||
                    draggedBlock.getType() != BlockTypes.WallInFrontCondition)) {

                closeBlock = uiProgramArea.getStatementBlockBodyPlugWithinRadius(draggedBlock,radius);
                connectionPoint = (closeBlock != null) ?
                        ((UIStatementBlock)closeBlock).getBodyPlugPosition(uiProgramArea)
                        : null;
                if(closeBlock != null) addBlockToBody(draggedBlock,closeBlock);
            }


            if (closeBlock != null) {
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
                ((StatementBlock)draggedBlock.getParentStatement().getBlock()).getBody().getBlocks().remove(draggedBlock);
            }
            //TODO: backend: remove currently selected block
            uiPalette.setHiddenStateAs(false);
        }
    }


//    /**
//     * Finds a viable position within the radius to translate to and adds the com.swop.blocks to the backend
//     * @param draggedBlock The new block
//     * @param radius The radius
//     * @return A viable position or null
//     */
//    private Point getConWithinRadius(UIBlock draggedBlock, int radius) {
//        for (UIBlock b : uiProgramArea.getUiBlocks()) {
//            if(draggedBlock.getPosition() == b.getPosition()) continue; //this block
//            if(draggedBlock instanceof UIConditionBlock) //Condition to statement
//            {
//                if(b instanceof UIStatementBlock){
//                    Point con = ((UIStatementBlock) b).getConditionPlugPosition();
//                    if (getDistance(draggedBlock.getPosition(), con) < radius) {
//                        addBlockToConditions(draggedBlock,b);
//                        closeBlock = b;
//                        return con;
//                    }
//                }
//                else if(b instanceof  UIConditionBlock){ //Condition to condition
//                    for (Point con : b.getConnectionPoints()) {
//                        if (getDistance(draggedBlock.getPosition(), con) < radius) {
//                            closeBlock = b;
//                            return con;
//                        }
//                    }
//                }
//                else continue;
//            }
//            else { //Statement/Action to Statement/Action
//                int i = 0;
//                for (Point con : b.getConnectionPoints()) {
//                    if (getDistance(draggedBlock.getPosition(), con) < radius) {
//                        if(i > 0) {
//                            addBlockToBody(draggedBlock,b);
//                        }
//                        closeBlock = b;
//                        return con;
//                    }
//                    i++;
//                }
//            }
//        }
//        closeBlock = null;
//        return null;
//    }

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
        /*int gS = ((UIStatementBlock) b).getGapSize() + draggedBlock.getHeight();
        ((UIStatementBlock) b).setGapSize(gS);*/
    }
}
