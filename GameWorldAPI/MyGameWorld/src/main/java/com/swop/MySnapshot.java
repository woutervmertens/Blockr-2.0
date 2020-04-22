package com.swop;

public class MySnapshot implements Snapshot{
    private NumSquare[][] grid;

    public NumSquare[][] getGrid() {
        return grid;
    }

    public void setGrid(NumSquare[][] grid) {
        this.grid = grid.clone();
    }
}
