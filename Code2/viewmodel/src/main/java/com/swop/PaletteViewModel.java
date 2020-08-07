package com.swop;

import com.swop.blocks.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The logic for the Palette View.
 */
public class PaletteViewModel extends ScrollableViewModel {
    private PaletteModel model;
    private WindowGameControllerFacade gameController;
    private StdBlockData defAcData;
    private StdBlockData defPrData;
    private StdBlockData defBodData;
    public PaletteViewModel(Point pos, int width, int height, WindowGameControllerFacade gameController) {
        super(pos, width, height);
        model = new PaletteModel();
        this.gameController = gameController;
        defAcData = new DefaultActionData();
        defPrData = new DefaultPredicateData();
        defBodData = new DefaultBodyBlockData();
        fillModelWithSupportedBlocks(gameController);
    }

    /**
     * Fills the PaletteModel with BlockButtons for every supported BlockModel.
     * @param gameController a facade to talk to the GameController
     */
    private void fillModelWithSupportedBlocks(WindowGameControllerFacade gameController) {
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
        model.setButtons(blockBtnModels);
    }

    public boolean isHidden(){
        return model.isHidden();
    }

    /**
     * @return a Collection of BlockButtons
     */
    public Collection<BlockButtonViewModel> getAllButtons(){
        Collection<BlockButtonModel> bms = model.getButtons();
        Collection<BlockButtonViewModel> bs = new ArrayList<>();
        for (BlockButtonModel bm : bms){
            bs.add(new BlockButtonViewModel(bm,gameController));
        }
        return bs;
    }

    /**
     * Adds a FunctionCallButton linked to a given FunctionDefinitionBlockModel.
     * @param refDef the FunctionDefinitionModel to reference in the Call
     */
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

    /**
     * Rempoves all FunctionCallButtons referencing the given FunctionDefinitionBlockModel
     * @param refDef the FunctionDefinitionModel referenced in the Call
     */
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

    /**
     * Adapts the position and width of all the buttons and resets the model data, then calls to handle the scrollbar
     */
    private void adaptButtons() {
        List<BlockButtonModel> btns = (List<BlockButtonModel>) model.getButtons();
        model.setFreeY(position.y);
        for (BlockButtonModel btn : btns){
            btn.setPosition(new Point(position.x,model.getFreeY()));
            btn.setWidth(getWidth() - scrollBarViewModel.getWidth() - 1);
            model.increaseFreeY(btn.getHeight());
        }
        model.setButtons(btns);
        adaptScrollbar();
    }

    /**
     * Adjusts the FunctionDefinitionButton to a new BlockModel with a different tag.
     */
    public void adjustDefinitionButton(){
        BlockButtonModel defBtn = model.getFuncDefBtn();
        defBtn.setBlockModel(defBtn.getBlockModel().clone());
        ((FunctionDefinitionBlockModel)defBtn.getBlockModel()).setText("" + model.getFreeDefTag());
        model.increaseDefTag();
    }

    /**
     * If any BlockButton is in the ScrollBuffer; activates the scrollbar and increases the size of the scrollable area.
     */
    private void adaptScrollbar(){
        Optional o = model.getButtons().stream().filter(x -> isInScrollBuffer(x.getPosition())).findAny();
        if(o.isPresent()) {
            scrollBarViewModel.setActive(true);
            increaseSize();
        }
    }

    /**
     * Reacts to a block being created in the Program Area; if it is a Function Definition, creates a Function Call Button for it
     * @param blockDropped the BlockModel dropped in the Program Area
     */
    public void reactToBlockCreate(BlockModel blockDropped){
        if(blockDropped.getBlockModelType() == BlockModelType.FUNCDEF) {
            addFuncCallButton((FunctionDefinitionBlockModel)blockDropped);
            adjustDefinitionButton();
        }
    }

    /**
     * Reacts to a block being removed from the Program Area; if it is a Function Definition, removes the Function Call Button for it
     * @param removedBlock the block being removed from the Program Area
     */
    public void reactToBlockRemove(BlockModel removedBlock){
        if(removedBlock.getBlockModelType() == BlockModelType.FUNCDEF) {
            removeFuncCallButtons((FunctionDefinitionBlockModel)removedBlock);
        }
    }

    /**
     * React to a MouseRelease on (x,y):
     *  Passes the call along to the scrollbar
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
    @Override
    public void HandleMousePress(int x, int y) {
        int oldy = y;
        if(!isWithin(x,y)) return;
        y = offsetScrollPosition(y);
        Collection<BlockButtonModel> bms = model.getButtons();
        for (BlockButtonModel bm : bms){
            BlockButtonViewModel b = new BlockButtonViewModel(bm,gameController);
            b.HandleClick(x,y);
        }
        scrollBarViewModel.HandleMousePress(x,oldy);
    }

    /**
     * React to a MouseRelease on (x,y):
     *  Passes the call along to the scrollbar
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
    @Override
    public void HandleMouseRelease(int x, int y) {
        scrollBarViewModel.HandleMouseRelease(x,y);
    }

    /**
     * React to a MouseRelease on (x,y):
     *  Passes the call along to the scrollbar
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
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

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        adaptButtons();
    }
}
