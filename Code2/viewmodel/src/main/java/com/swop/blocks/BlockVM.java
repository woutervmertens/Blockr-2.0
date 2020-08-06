package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.ProgramAreaModel;
import com.swop.SuccessState;

import java.awt.*;

public class BlockVM{
    protected BlockModel model;

    public BlockVM(BlockModel model){
        this.model = model;
        model.renewConnectors();
    }

    public BlockModel getModel() { return model; }

    public int getHeight(){return model.getHeight();}

    public Point getPosition() { return model.getPosition();}

    public SuccessState Execute(GameWorld gw, ProgramAreaModel b) {return null;}

    public BlockViewData getViewData(){
        return new BlockViewData(model.getText(),model.getTextPosition(),model.getColor(),model.getPolygon());
    }

    /**
     * Sets the links, calls remove on body
     * @param parent the parent of this Block
     */
    public void Remove(BlockModel parent) {
        if(parent != null){
            BlockVM parentVM = BlockFactory.getInstance().createBlockVM(parent);
            parentVM.replaceChild(model);
        }
        else if(model.getNext() != null){
            model.getNext().setIsFirstFlag(true);
            model.setNextBlock(null);
        }
    }

    /**
     * Takes old child, and replaces with next in line
     * @param model old child
     */
    public void replaceChild(BlockModel model){
        if(this.model.getNext() == model)
            this.model.setNextBlock(model.getNext());
    }

    /**
     * Updates the position and handles the Connectors positions
     * @param ConnectorPos the new position
     */
    public void updatePosition(Point ConnectorPos){
        if(ConnectorPos != null) model.position = (Point) ConnectorPos.clone();
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
    }

    /**
     * Returns a Connector for which the position and BlockModelType are valid or returns null.
     * @param position the position to check
     * @param blockModelType the BlockModelType to check
     * @return Connector or null
     */
    public Connector getConnectorOrNull(Point position, BlockModelType blockModelType){
        if(model.nextConnector == null) return null;
        //Not condition or funcdef
        if(!(blockModelType == BlockModelType.CONDITION || blockModelType == BlockModelType.FUNCDEF) && model.nextConnector.isOnConnector(position))
            return model.nextConnector;
        return null;
    }
}
