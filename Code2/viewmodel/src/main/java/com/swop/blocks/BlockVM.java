package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

import java.awt.*;

public class BlockVM implements Cloneable {
    protected BlockModel model;

    public BlockVM(BlockModel model){
        this.model = model;
    }
    /**
     * @return Returns a clone of the given block.
     */
    public BlockVM clone() {
        try {
            return (BlockVM) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BlockModel getModel() { return model; }

    public int getHeight(){return model.getHeight();}

    public void setPosition(Point pos){
        model.setPosition(pos);
    }

    public Point getPosition() { return model.getPosition();}

    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {return null;}

    public BlockViewData getViewData(){
        return new BlockViewData(model.getText(),model.getTextPosition(),model.getColor(),model.getPolygon());
    }

    //Sets the links, calls remove on body
    public void Remove(BlockModel parent) {
        if(parent != null){
            BlockVM parentVM = BlockFactory.getInstance().createBlockVM(parent);
            parentVM.setNext(model.getNext());
        }
        else if(model.getNext() != null)model.getNext().setIsFirstFlag(true);
    }

    public void updatePosition(Point ConnectorPos){
        if(ConnectorPos != null) model.position = ConnectorPos;
        model.updateConnectors();
    };

    public BlockModel getNext() {
        return model.getNext();
    }

    public Point getNextPosition(){
        return model.nextConnector.getPosition();
    }

    public void setNext(BlockModel next) {
        this.model.setNextBlock(next);
        model.setIsLastFlag(next == null);
    }

    public Connector getConnectorOrNull(Point position){
        if(model.nextConnector == null) return null;
        if(model.nextConnector.isOnConnector(position))
            return model.nextConnector;
        return null;
    }
}
