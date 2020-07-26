package com.swop.blocks;

import java.awt.*;

public class StdBlockData {
    private int width, height, titleHeight, titleWidth, pillarWidth, gapSize;
    private Point position;
    private String text;

    public StdBlockData(Point position, int width, int height, String text){
        this(position,width,height,30,10,0,text);
    }
    public StdBlockData(Point position, int width, int height, int titleHeight, int pillarWidth, int gapSize, String text){
        this.width = width;
        this.height = height;
        this.position = position;
        this.titleHeight = titleHeight;
        this.pillarWidth = pillarWidth;
        this.gapSize = gapSize;
        this.text = text;
        this.titleWidth = width - 20;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getPosition() {
        return position;
    }

    public String getText() {
        return text;
    }

    public int getTitleHeight() {
        return titleHeight;
    }

    public int getPillarWidth() {
        return pillarWidth;
    }

    public int getGapSize() {
        return gapSize;
    }

    public int getTitleWidth() {return titleWidth;}
}
