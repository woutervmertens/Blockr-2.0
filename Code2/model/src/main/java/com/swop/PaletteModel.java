package com.swop;

import com.swop.blocks.BlockModel;

import java.util.Collection;

public class PaletteModel implements Cloneable{
    private boolean isHidden = false;
    private Collection<BlockModel> supportedBlocks;

    public Collection<BlockModel> getSupportedBlocks(){
        return supportedBlocks;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public void setSupportedBlocks(Collection<BlockModel> supportedBlocks) {
        this.supportedBlocks = supportedBlocks;
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
