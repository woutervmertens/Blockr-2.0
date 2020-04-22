package com.swop;

import java.awt.*;

public class MySnapshot implements Snapshot{
    private NumSquare[][] grid;
    private Point emptySquare;

    public NumSquare[][] getGrid() {
        return grid;
    }

    public Point getEmptySquare() {
        return emptySquare;
    }

    public void setGrid(NumSquare[][] grid) {
        this.grid = grid.clone();
    }

    public void setEmptySquare(Point emptySquare) {
        this.emptySquare = (Point) emptySquare.clone();
    }
}
