package com.swop.blocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A block that defines a function.
 */
public class FunctionDefinitionBlockModel extends BlockModelWithBody{
    //List<FunctionCallBlockModel> calls = new ArrayList<>();
    /**
     * Creates a block that defines a function with the given position, width and height.
     *
     * @param data standard data container for the Block
     */
    public FunctionDefinitionBlockModel(StdBlockData data) {
        super(data);
        this.color = Color.white;
        this.highlightColor = Color.white;
        setIsFirstFlag(false);
        blockModelType = BlockModelType.FUNCDEF;
    }

    public void setText(String txt){
        this.text = txt;
    }

    /**
     * Clones the object.
     * @return A copy of this FunctionDefinitionBlockModel object.
     */
    @Override
    public BlockModel clone() {
        FunctionDefinitionBlockModel cf = new FunctionDefinitionBlockModel(new StdBlockData(new Point(getPosition().x,getPosition().y),getWidth(),height,getText()));
        cf.setHighlightState(isHighlight);
        cf.setNextBlock(getNext());
        cf.setFirstBodyBlockModel(getFirstBodyBlockModel());
        cf.setGapSize(getGapSize());
        cf.setIsFirstFlag(isFirst());
        return cf;
    }

    /**
     * Creates and return the polygon for the View to display.
     * @return a Polygon object
     */
    @Override
    public Polygon getPolygon() {
        Polygon pol = new Polygon();
        pol.addPoint(position.x, position.y);
        pol.addPoint(position.x + width, position.y);

        pol.addPoint(position.x + width, position.y + height);

        //plug body
        pol.addPoint(position.x + pillarWidth + step * 4, position.y + height);
        pol.addPoint(position.x + pillarWidth + step * 3, position.y + height + step);
        pol.addPoint(position.x + pillarWidth + step * 2, position.y + height);

        //gap
        pol.addPoint(position.x + pillarWidth, position.y + height);
        pol.addPoint(position.x + pillarWidth, position.y + height + getGapSize());

        //socket body
        pol.addPoint(position.x + pillarWidth + step * 4, position.y + height + getGapSize());
        pol.addPoint(position.x + pillarWidth + step * 3, position.y + height + step + getGapSize());
        pol.addPoint(position.x + pillarWidth + step * 2, position.y + height + getGapSize());

        //gap bottom
        pol.addPoint(position.x + width, position.y + getGapSize() + height);
        pol.addPoint(position.x + width, position.y + height + pillarWidth + getGapSize());

        pol.addPoint(position.x, position.y + height + pillarWidth + getGapSize());
        return pol;
    }
}
