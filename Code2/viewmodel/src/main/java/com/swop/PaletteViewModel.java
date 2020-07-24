package com.swop;

import com.swop.blocks.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class PaletteViewModel extends ScrollableViewModel {
    private PaletteModel model;
    private GameController gameController;
    public PaletteViewModel(Point pos, int width, int height, GameController gameController) {
        super(pos, width, height);
        model = new PaletteModel();
        this.gameController = gameController;
        fillModelWithSupportedBlocks(gameController);
    }

    private void fillModelWithSupportedBlocks(GameController gameController) {
        ArrayList<BlockButtonModel> blockBtnModels = new ArrayList<>();
        StdBlockData defAcData = gameController.getDefaultActionData();
        StdBlockData defPrData = gameController.getDefaultPredicateData();
        StdBlockData defBodData = gameController.getDefaultBodyBlockData();
        int x = position.x;
        int y = position.y;
        for(Action action : gameController.getSupportedActions()){
            blockBtnModels.add(new BlockButtonModel(new Point(x,y),width,defAcData.getHeight() + 10,
                    new ActionBlockModel(
                    new StdBlockData(
                            new Point(x,y),
                            defAcData.getWidth(),
                            defAcData.getHeight(),
                            action.toString()),action)));
            y += defAcData.getHeight() + 10;
        }
        for(Predicate predicate : gameController.getSupportedPredicates()){
            blockBtnModels.add(new BlockButtonModel(new Point(x,y),width,defPrData.getHeight() + 10,
                    new ConditionBlockModel(
                    new StdBlockData(
                            new Point(x,y),
                            defPrData.getWidth(),
                            defPrData.getHeight(),
                            predicate.toString()),true,predicate)));
            y += defPrData.getHeight() + 10;
        }
        //NOT
        blockBtnModels.add(new BlockButtonModel(new Point(x,y),width,defPrData.getHeight() + 10,
                new ConditionBlockModel(
                new StdBlockData(
                        new Point(x,y),
                        defPrData.getWidth(),
                        defPrData.getHeight(),
                        "Not"),false,null)));
        y += defPrData.getHeight() + 10;
        //IF
        blockBtnModels.add(new BlockButtonModel(new Point(x,y),width,defBodData.getHeight() + defBodData.getPillarWidth() + 10,
                new IfBlockModel(
                new StdBlockData(
                        new Point(x,y),
                        defBodData.getWidth(),
                        defBodData.getHeight(),
                        "If"),defPrData.getWidth() + 10)));
        y += defBodData.getHeight() + defBodData.getPillarWidth() + 10;
        //WHILE
        blockBtnModels.add(new BlockButtonModel(new Point(x,y),width,defBodData.getHeight() + defBodData.getPillarWidth() + 10,
                new WhileBlockModel(
                new StdBlockData(
                        new Point(x,y),
                        defBodData.getWidth(),
                        defBodData.getHeight(),
                        "While"),defPrData.getWidth() + 10)));
        y += defBodData.getHeight() + defBodData.getPillarWidth() + 10;
        //FUNCTION DEFINITION
        FunctionDefinitionBlockModel fDefMod = new FunctionDefinitionBlockModel(
                new StdBlockData(
                        new Point(x,y),
                        defBodData.getWidth(),
                        defBodData.getHeight(),
                        "0"));
        blockBtnModels.add(new BlockButtonModel(new Point(x,y),width,defBodData.getHeight() + defBodData.getPillarWidth() + 10,fDefMod));
        y += defBodData.getHeight() + defBodData.getPillarWidth() + 10;
        //FUNCTION CALL
        blockBtnModels.add(new BlockButtonModel(new Point(x,y),width,defAcData.getHeight() + 10,new FunctionCallBlockModel(
                new StdBlockData(
                        new Point(x,y),
                        defAcData.getWidth(),
                        defAcData.getHeight(),
                        "0"),fDefMod)));

        model.setSupportedBlocks(blockBtnModels);
    }

    public boolean isHidden(){
        return model.isHidden();
    }

    public Collection<BlockButton> getAllButtons(){
        Collection<BlockButtonModel> bms = model.getSupportedBlocks();
        Collection<BlockButton> bs = new ArrayList<>();
        for (BlockButtonModel bm : bms){
            bs.add(new BlockButton(bm,gameController));
        }
        return bs;
    }

    @Override
    public void HandleMousePress(int x, int y) {
        if(!isWithin(x,y)) return;
        Collection<BlockButtonModel> bms = model.getSupportedBlocks();
        for (BlockButtonModel bm : bms){
            BlockButton b = new BlockButton(bm,gameController);
            b.HandleClick(x,y);
        }
        scrollBarViewModel.HandleMousePress(x,y);
    }

    @Override
    public void HandleMouseRelease(int x, int y) {
        scrollBarViewModel.HandleMouseRelease(x,y);
    }

    @Override
    public void HandleMouseDrag(int x, int y) {
        scrollBarViewModel.HandleMouseDrag(x,y);
    }

    @Override
    public void HandleReset() {
        return;
    }

    @Override
    public PaletteModel getModel() {
        return model;
    }

    public void setModel(PaletteModel model){
        this.model = model;
    }
}
