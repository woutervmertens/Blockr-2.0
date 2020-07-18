package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

import java.awt.*;

public class Block implements Cloneable {
    BlockModel model;
    public Block(BlockModel model){
        this.model = model;
    }
    /**
     * @return Returns a clone of the given block.
     */
    public Block clone() {
        try {
            return (Block) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setPosition(Point pos){
        model.setPosition(pos);
    }

    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {return null;}

    public BlockViewData getViewData(){
        return new BlockViewData(model.getText(),model.getTextPosition(),model.getColor(),model.getPolygon());
    }

    public void prepareRemoval() {
        return;//TODO: links the block below to the block above, or the block to the right, with the block to the left
    }

    public Connector findParentConnector() {
        return null; //TODO: returns the parent connector
    }
}
