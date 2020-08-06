package com.swop;

import com.swop.blocks.BlockModelType;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The data for the Palette Window.
 */
public class PaletteModel implements Cloneable{
    private boolean isHidden = false;
    private int freeY = 0;
    private int freeDefTag = 0;
    private Collection<BlockButtonModel> buttons;

    public Collection<BlockButtonModel> getButtons(){
        return buttons;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public void setButtons(Collection<BlockButtonModel> buttons) {
        this.buttons = buttons;
    }

    public void addButton(BlockButtonModel b){
        buttons.add(b);
    }

    public void removeButton(BlockButtonModel b){
        buttons.remove(b);
    }

    public int getFreeY() {
        return freeY;
    }

    public int getFreeDefTag(){
        return freeDefTag;
    }

    public void increaseDefTag(){
        freeDefTag++;
    }

    public void setFreeDefTag(int freeDefTag){
        this.freeDefTag = freeDefTag;
    }

    public void increaseFreeY(int incr){
        freeY += incr;
    }

    public void setFreeY(int freeY) {
        this.freeY = freeY;
    }

    /**
     * @return Returns a clone of the given model.
     */
    public PaletteModel clone() {
        PaletteModel cp = new PaletteModel();
        cp.setHidden(isHidden);
        cp.setFreeY(freeY);
        cp.setFreeDefTag(freeDefTag);
        Collection<BlockButtonModel> shallowClone = new ArrayList<>();
        for (BlockButtonModel bbm : buttons) shallowClone.add(bbm.clone());
        for (BlockButtonModel bbm : shallowClone) bbm.setBlockModel(bbm.getBlockModel().clone());
        cp.setButtons(shallowClone);
        return cp;
    }

    /**
     * Filters through all the buttons to find the one representing the Function Definition
     * @return The BlockButtonModel for the FunctionDefinition or null
     */
    public BlockButtonModel getFuncDefBtn() {
        BlockButtonModel defBtn = buttons.stream().filter(x -> x.getBlockModel().getBlockModelType() == BlockModelType.FUNCDEF).findFirst().orElse(null);
        if(defBtn == null) throw new IllegalStateException("PaletteModel.getFuncDefBtn(): No FunctionDefinition Button found!");
        return defBtn;
    }

}
