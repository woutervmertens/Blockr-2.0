import UIElements.UIBlock;
import UIElements.UIStatementBlock;
import UIElements.BlockTypes;
import blocks.ConditionBlock;
import blocks.StatementBlock;

import java.awt.*;

public class DisplaceBlockHandler {
    private UIProgramArea uiProgramArea;
    private UIPalette uiPalette;
    private ExecuteProgramHandler executeProgramHandler;
    private final int radius = 20;  // Radius for connections

    public DisplaceBlockHandler(UIProgramArea uiProgramArea, UIPalette uiPalette, UIGameWorld uiGameWorld, BlockrGame blockrGame) {
        this.uiProgramArea = uiProgramArea;
        this.uiPalette = uiPalette;

        executeProgramHandler = new ExecuteProgramHandler(uiProgramArea, uiGameWorld, blockrGame);
    }

    /**
     * @param draggedBlock The block that was dragged until release (if any)
     */
    public void handleRelease(int x, int y, UIBlock draggedBlock) {
        if (draggedBlock == null) throw new IllegalArgumentException("No block was dragged !");

        UIBlock closeBlock = null;
        Point connectionPoint = null;

        //TODO: create block where needed
        if (uiProgramArea.isWithin(x, y)) {
            Point dropPos = new Point(x, y);

            // 1) EVENTUAL PLUG
            closeBlock = uiProgramArea.getBlockWithPlugForBlockWithinRadius(draggedBlock, radius);
            connectionPoint = (closeBlock != null) ?
                    new Point(closeBlock.getPosition().x, closeBlock.getPosition().y + closeBlock.getHeight() + 5)
                    : null;

            // 2) EVENTUAL SOCKET
            if (closeBlock == null) {
                closeBlock = uiProgramArea.getBlockWithSocketForBlockWithinRadius(draggedBlock, radius);
                connectionPoint = (closeBlock != null) ?
                        new Point(closeBlock.getPosition().x, closeBlock.getPosition().y - closeBlock.getHeight() - 5)
                        : null;
            }

            // 3) EVENTUAL CONDITION
            if (closeBlock == null && (draggedBlock.getType() == BlockTypes.NotCondition ||
                                       draggedBlock.getType() == BlockTypes.WallInFrontCondition)) {
                try {
                    // TODO: (UIStatementBlock)
                } catch (ClassCastException e) {}
            }

            if (closeBlock != null) {
                //TODO: connect this block with the close block
                //TODO: handle backend
                System.out.println("Close block: " + closeBlock.getText());
                draggedBlock.setPosition(connectionPoint);
            } else {
                //TODO: create new program in backend
                draggedBlock.setPosition(dropPos);
            }
            uiProgramArea.addBlock(draggedBlock);
            //Backend add block to current blockgroup
            executeProgramHandler.addBlockToProgram(draggedBlock);
            executeProgramHandler.reset();


        } else {
            uiProgramArea.removeBlock(draggedBlock);
            //TODO: backend: remove currently selected block
            uiPalette.setHidden(false);
        }


    }


//    /**
//     * Finds a viable position within the radius to translate to and adds the blocks to the backend
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
        int gS = ((UIStatementBlock) b).getGapSize() + draggedBlock.getHeight();
        ((UIStatementBlock) b).setGapSize(gS);
    }


}
