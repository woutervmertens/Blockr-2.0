package com.swop;

import com.swop.blocks.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class PaletteViewModel extends ViewModel {
    private PaletteModel model;
    public PaletteViewModel(Point pos, int width, int height, GameController gameController) {
        super(pos, width, height);
        model = new PaletteModel();
        fillModelWithSupportedBlocks(gameController);
    }

    private void fillModelWithSupportedBlocks(GameController gameController) {
        ArrayList<BlockModel> blockModels = new ArrayList<>();
        StdBlockData defAcData = gameController.getDefaultActionData();
        StdBlockData defPrData = gameController.getDefaultPredicateData();
        StdBlockData defBodData = gameController.getDefaultBodyBlockData();
        int x = position.x + 15;
        int y = position.y + 10;
        for(Action action : gameController.getSupportedActions()){
            blockModels.add(new ActionBlockModel(
                    new StdBlockData(
                            new Point(x,y),
                            defAcData.getWidth(),
                            defAcData.getHeight(),
                            action.toString()),action));
            y += defAcData.getHeight();
        }
        for(Predicate predicate : gameController.getSupportedPredicates()){
            blockModels.add(new ConditionBlockModel(
                    new StdBlockData(
                            new Point(x,y),
                            defPrData.getWidth(),
                            defPrData.getHeight(),
                            predicate.toString()),true,predicate));
            y += defPrData.getHeight();
        }
        //NOT
        blockModels.add(new ConditionBlockModel(
                new StdBlockData(
                        new Point(x,y),
                        defPrData.getWidth(),
                        defPrData.getHeight(),
                        "Not"),false,null));
        y += defPrData.getHeight();
        //IF
        blockModels.add(new IfBlockModel(
                new StdBlockData(
                        new Point(x,y),
                        defBodData.getWidth(),
                        defBodData.getHeight(),
                        "If")));
        y += defBodData.getHeight();
        //WHILE
        blockModels.add(new WhileBlockModel(
                new StdBlockData(
                        new Point(x,y),
                        defBodData.getWidth(),
                        defBodData.getHeight(),
                        "While")));
        y += defBodData.getHeight();
        //FUNCTION DEFINITION
        FunctionDefinitionBlockModel fDefMod = new FunctionDefinitionBlockModel(
                new StdBlockData(
                        new Point(x,y),
                        defBodData.getWidth(),
                        defBodData.getHeight(),
                        "0"));
        blockModels.add(fDefMod);
        y += defBodData.getHeight();
        //FUNCTION CALL
        blockModels.add(new FunctionCallBlockModel(
                new StdBlockData(
                        new Point(x,y),
                        defAcData.getWidth(),
                        defAcData.getHeight(),
                        "0"),fDefMod));
    }

    public boolean isHidden(){
        return model.isHidden();
    }

    public Collection<Block> getAllBlocks(){
        Collection<BlockModel> bms = model.getSupportedBlocks();
        Collection<Block> bs = new ArrayList<>();
        for (BlockModel bm : bms){
            bs.add(new Block(bm));
        }
        return bs;
    }

    /**
     * Get type of the area clicked on (assuming that the click is on the palette area).
     *
     * @custom.pre isWithin(x, y);
     * @param x Click x coordinate.
     * @param y Click y coordinate.
     * @return The Block of the clicked element.
     */
    public Block getBlockClicked(int x, int y) {
        assert isWithin(x, y);

        Collection<BlockModel> bms = model.getSupportedBlocks();
        for (BlockModel bm : bms){
            if(bm.isWithin(x,y)) return new Block(bm);
        }
        return null;
    }

    @Override
    public void HandleMousePress(int x, int y) {
        if(!isWithin(x,y)) return;
        //TODO
    }

    @Override
    public void HandleMouseRelease(Block draggedBlock, int x, int y) {
        return;
    }

    @Override
    public void HandleMouseDrag(int x, int y) {

    }
}
