package com.swop.blocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A block that defines a function.
 */
public class FunctionDefinitionBlockModel extends BlockModelWithBody{
    List<FunctionCallBlockModel> calls = new ArrayList<>();
    /**
     * Creates a block that defines a function with the given position, width and height.
     */
    public FunctionDefinitionBlockModel(StdBlockData data) {
        super(data);
        this.color = Color.white;
        this.highlightColor = Color.white;
    }

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
        pol.addPoint(position.x + pillarWidth, position.y + height + gapSize);

        //socket body
        pol.addPoint(position.x + pillarWidth + step * 4, position.y + height + gapSize);
        pol.addPoint(position.x + pillarWidth + step * 3, position.y + height + step + gapSize);
        pol.addPoint(position.x + pillarWidth + step * 2, position.y + height + gapSize);

        //gap bottom
        pol.addPoint(position.x + width, position.y + gapSize + height);
        pol.addPoint(position.x + width, position.y + height + pillarWidth + gapSize);

        pol.addPoint(position.x, position.y + height + pillarWidth + gapSize);
        return pol;
    }

    public List<FunctionCallBlockModel> getCalls() {
        return calls;
    }

    public void addCall(FunctionCallBlockModel callBlock) {
        calls.add(callBlock);
    }

    public void removeCall(FunctionCallBlockModel callBlock) {
        calls.remove(callBlock);
    }
}
