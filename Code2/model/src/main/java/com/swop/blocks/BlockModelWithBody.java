package com.swop.blocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BlockModelWithBody extends BlockModel{
    private List<BlockModel> bodyBlockModels = new ArrayList<>();
    private BlockModel firstBodyBlockModel = null;
    private int gapSize;
    private int titleHeight;
    protected final int pillarWidth;

    protected Connector bodyConnector;
    private Point bodyOffset;

    /**
     * Creates a block that can have a body.
     */
    protected BlockModelWithBody(StdBlockData data) {
        super(data);
        this.titleHeight = data.getTitleHeight();
        this.pillarWidth = data.getPillarWidth();
        this.gapSize = data.getGapSize();
        bodyOffset = ConnectorType.BODY.getOffset(data);
        bodyConnector = new Connector(pointSum(position,bodyOffset),ConnectorType.BODY);
    }

    public BlockModel getFirstBodyBlockModel() {
        return firstBodyBlockModel;
    }

    public void setFirstBodyBlockModel(BlockModel firstBodyBlockModel) {
        this.firstBodyBlockModel = firstBodyBlockModel;
    }

    private void fillBody(){
        bodyBlockModels.clear();
        int newGapSize = 0;
        //Fill conditions
        BlockModel nextChild = firstBodyBlockModel;
        while(nextChild != null){
            bodyBlockModels.add(nextChild);
            newGapSize += nextChild.getHeight();
            nextChild = nextChild.nextBlock;
        }
        setGapSize(newGapSize);
    }

    public List<BlockModel> getBodyBlockModels() {
        fillBody();
        return bodyBlockModels;
    }

    public int getGapSize() {
        return gapSize;
    }

    public void setGapSize(int gapSize) {
        this.gapSize = gapSize;
    }

    @Override
    public abstract Polygon getPolygon();

    @Override
    public int getHeight() {
        int height = pillarWidth;
        height += getGapSize();
        height += titleHeight;
        return height;
    }

    @Override
    public void updateConnectors() {
        super.updateConnectors();
        if(bodyConnector == null) return;
        bodyConnector.setPosition(pointSum(position,bodyOffset));
    }

    @Override
    public void renewConnectors() {
        super.renewConnectors();
        if(bodyConnector == null) return;
        bodyConnector = new Connector(pointSum(position,bodyOffset),ConnectorType.BODY);
    }

    @Override
    public boolean hasConnectedBlock(BlockModel blockModel) {
        return super.hasConnectedBlock(blockModel) || blockModel == firstBodyBlockModel;
    }
}
