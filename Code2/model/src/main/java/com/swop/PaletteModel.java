package com.swop;

import com.swop.blocks.BlockModel;

import java.util.Collection;

public class PaletteModel {
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
}
