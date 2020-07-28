package com.swop;

import java.util.Collection;

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
        return freeDefTag++;
    }

    public void increaseFreeY(int incr){
        freeY += incr;
    }

    public void setFreeY(int freeY) {
        this.freeY = freeY;
    }

    /**
     * @return Returns a clone of the given block.
     */
    public PaletteModel clone() {
        try {
            return (PaletteModel) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
