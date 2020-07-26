package com.swop.blocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BlockModelWithBody extends BlockModel{
    protected List<BlockModel> bodyBlockModels = new ArrayList<>();
    private BlockModel nextBodyBlockModel = null;
    protected int gapSize;
    protected int titleHeight;
    protected int pillarWidth;

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
        bodyOffset = ConnectorType.INNER_TOP.getOffset(data);
        bodyConnector = new Connector(pointSum(position,bodyOffset));
    }

    public BlockModel getNextBodyBlockModel() {
        return nextBodyBlockModel;
    }

    public void setNextBodyBlockModel(BlockModel nextBodyBlockModel) {
        this.nextBodyBlockModel = nextBodyBlockModel;
    }


    public List<BlockModel> getBodyBlockModels() {
        return bodyBlockModels;
    }

    public int getGapSize() {
        return gapSize;
    }

    public void setGapSize(int gapSize) {
        this.gapSize = gapSize;
    }

    /**
     * Increases the gap size with the given size.
     * @param increase the given size
     */
    public void increaseGapSize(int increase) {
        this.setGapSize(getGapSize() + increase);
    }

    @Override
    public abstract Polygon getPolygon();

    /**
     * @return Returns the number of blocks: blockWithBody + # bodyBlocks
     */
    @Override
    public int getCount() {
        int count = 1;
        for (BlockModel blockModel : getBodyBlockModels()) {
            count += blockModel.getCount();
        }
        return count;
    }

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
    public boolean hasConnectedBlock(BlockModel blockModel) {
        return super.hasConnectedBlock(blockModel) || blockModel == nextBodyBlockModel;
    }
}
