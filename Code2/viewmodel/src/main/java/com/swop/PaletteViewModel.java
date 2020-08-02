package com.swop;

import com.swop.blocks.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PaletteViewModel extends ScrollableViewModel {
    private PaletteModel model;
    private GameController gameController;
    private StdBlockData defAcData;
    private StdBlockData defPrData;
    private StdBlockData defBodData;
    public PaletteViewModel(Point pos, int width, int height, GameController gameController) {
        super(pos, width, height);
        model = new PaletteModel();
        this.gameController = gameController;
        defAcData = gameController.getDefaultActionData();
        defPrData = gameController.getDefaultPredicateData();
        defBodData = gameController.getDefaultBodyBlockData();
        fillModelWithSupportedBlocks(gameController);
    }

    private void fillModelWithSupportedBlocks(GameController gameController) {
        ArrayList<BlockButtonModel> blockBtnModels = new ArrayList<>();

        int x = position.x;
        model.setFreeY(position.y);
        for(Action action : gameController.getSupportedActions()){
            blockBtnModels.add(new BlockButtonModel(new Point(x,model.getFreeY()),width-10,defAcData.getHeight() + 10,
                    new ActionBlockModel(
                    new StdBlockData(
                            new Point(x,model.getFreeY()),
                            defAcData.getWidth(),
                            defAcData.getHeight(),
                            action.toString()),action)));
            model.increaseFreeY(defAcData.getHeight() + 10);
        }
        for(Predicate predicate : gameController.getSupportedPredicates()){
            blockBtnModels.add(new BlockButtonModel(new Point(x,model.getFreeY()),width-10,defPrData.getHeight() + 10,
                    new ConditionBlockModel(
                    new StdBlockData(
                            new Point(x,model.getFreeY()),
                            defPrData.getWidth(),
                            defPrData.getHeight(),
                            predicate.toString()),true,predicate)));
            model.increaseFreeY(defPrData.getHeight() + 10);
        }
        //NOT
        blockBtnModels.add(new BlockButtonModel(new Point(x,model.getFreeY()),width-10,defPrData.getHeight() + 10,
                new ConditionBlockModel(
                new StdBlockData(
                        new Point(x,model.getFreeY()),
                        defPrData.getWidth(),
                        defPrData.getHeight(),
                        "Not"),false,null)));
        model.increaseFreeY(defPrData.getHeight() + 10);
        //IF
        blockBtnModels.add(new BlockButtonModel(new Point(x,model.getFreeY()),width-10,defBodData.getHeight() + defBodData.getPillarWidth() + 10,
                new IfBlockModel(
                new StdBlockData(
                        new Point(x,model.getFreeY()),
                        defBodData.getWidth(),
                        defBodData.getHeight(),
                        "If"),defPrData.getWidth() + 10)));
        model.increaseFreeY(defBodData.getHeight() + defBodData.getPillarWidth() + 10);
        //WHILE
        blockBtnModels.add(new BlockButtonModel(new Point(x,model.getFreeY()),width-10,defBodData.getHeight() + defBodData.getPillarWidth() + 10,
                new WhileBlockModel(
                new StdBlockData(
                        new Point(x,model.getFreeY()),
                        defBodData.getWidth(),
                        defBodData.getHeight(),
                        "While"),defPrData.getWidth() + 10)));
        model.increaseFreeY(defBodData.getHeight() + defBodData.getPillarWidth() + 10);
        //FUNCTION DEFINITION
        FunctionDefinitionBlockModel fDefMod = new FunctionDefinitionBlockModel(
                new StdBlockData(
                        new Point(x,model.getFreeY()),
                        defBodData.getWidth(),
                        defBodData.getHeight(),
                        "" + model.getFreeDefTag()));
        BlockButtonModel defBtn = new BlockButtonModel(new Point(x,model.getFreeY()),width-10,defBodData.getHeight() + defBodData.getPillarWidth() + 10,fDefMod);
        blockBtnModels.add(defBtn);
        model.increaseDefTag();
        model.increaseFreeY(defBodData.getHeight() + defBodData.getPillarWidth() + 10);
        /*//FUNCTION CALL (debug only)
        blockBtnModels.add(new BlockButtonModel(new Point(x,model.getFreeY()),width,defAcData.getHeight() + 10,new FunctionCallBlockModel(
                new StdBlockData(
                        new Point(x,model.getFreeY()),
                        defAcData.getWidth(),
                        defAcData.getHeight(),
                        "0"),fDefMod)));
        model.increaseFreeY(defAcData.getHeight() + 10);*/
        model.setButtons(blockBtnModels);
    }

    public boolean isHidden(){
        return model.isHidden();
    }

    public Collection<BlockButton> getAllButtons(){
        Collection<BlockButtonModel> bms = model.getButtons();
        Collection<BlockButton> bs = new ArrayList<>();
        for (BlockButtonModel bm : bms){
            bs.add(new BlockButton(bm,gameController));
        }
        return bs;
    }

    public void addFuncCallButton(FunctionDefinitionBlockModel refDef){
        model.addButton(new BlockButtonModel(new Point(position.x,model.getFreeY()),width,defAcData.getHeight() + 10,new FunctionCallBlockModel(
                new StdBlockData(
                        new Point(position.x,offsetScrollPosition(model.getFreeY())),
                        defAcData.getWidth(),
                        defAcData.getHeight(),
                        refDef.getText()),refDef)));
        model.increaseFreeY(defAcData.getHeight() + 10);
        adaptScrollbar();
    }

    public void removeFuncCallButtons(FunctionDefinitionBlockModel refDef){
        List<BlockButtonModel> callBtns = model.getButtons()
                .stream()
                .filter(x -> x.getBlockModel().getBlockModelType() == BlockModelType.FUNCCALL)
                .filter(x -> ((FunctionCallBlockModel)(x.getBlockModel())).getDefinitionBlock() == refDef)
                .collect(Collectors.toList());
        for (BlockButtonModel btn : callBtns){
            model.removeButton(btn);
        }
        adaptButtons();

    }

    private void adaptButtons() {
        List<BlockButtonModel> btns = (List<BlockButtonModel>) model.getButtons();
        model.setFreeY(position.y);
        for (BlockButtonModel btn : btns){
            btn.setPosition(new Point(position.x,model.getFreeY()));
            model.increaseFreeY(btn.getHeight());
        }
        model.setButtons(btns);
        adaptScrollbar();
    }

    public void adjustDefinitionButton(){
        BlockButtonModel defBtn = model.getFuncDefBtn();
        defBtn.setBlockModel(defBtn.getBlockModel().clone());
        ((FunctionDefinitionBlockModel)defBtn.getBlockModel()).setText("" + model.getFreeDefTag());
        model.increaseDefTag();
    }

    private void adaptScrollbar(){
        //TODO: if buttonModel is in the scrollbuffer: activate scroll or change scrollheight
        scrollBarViewModel.setActive(true);
        increaseSize();
    }

    public void reactToBlockCreate(BlockModel blockDropped){
        if(blockDropped.getBlockModelType() == BlockModelType.FUNCDEF) {
            addFuncCallButton((FunctionDefinitionBlockModel)blockDropped);
            adjustDefinitionButton();
        }
    }

    public void reactToBlockRemove(BlockModel blockDropped){
        if(blockDropped.getBlockModelType() == BlockModelType.FUNCDEF) {
            removeFuncCallButtons((FunctionDefinitionBlockModel)blockDropped);
        }
    }

    @Override
    public void HandleMousePress(int x, int y) {
        y = offsetScrollPosition(y);
        if(!isWithin(x,y)) return;
        Collection<BlockButtonModel> bms = model.getButtons();
        for (BlockButtonModel bm : bms){
            BlockButton b = new BlockButton(bm,gameController);
            b.HandleClick(x,y);
        }
        scrollBarViewModel.HandleMousePress(x,y);
    }

    @Override
    public void HandleMouseRelease(int x, int y) {
        gameController.setDraggedBlockVM(null);
        y = offsetScrollPosition(y);
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
