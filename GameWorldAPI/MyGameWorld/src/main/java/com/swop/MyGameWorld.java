package com.swop;

import java.awt.*;
import java.util.ArrayList;

public class MyGameWorld implements GameWorld {
    private NumSquare[][] grid, solutiongrid;
    private int size;
    private Point drawPosition;
    private Point emptySquare;

    public MyGameWorld(){
        this.grid = new NumSquare[3][3];
        this.solutiongrid = new NumSquare[3][3];
        this.size = 50;
        this.drawPosition = new Point(0,0);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public NumSquare[][] getGrid() {
        return grid;
    }

    public int getSize() {
        return size;
    }

    public Point getDrawPosition() {
        return drawPosition;
    }

    public Point getEmptySquare() {return emptySquare;}

    public void setDrawPosition(Point drawPosition) {
        this.drawPosition = drawPosition;
    }
    /**
     * Sets up a world grid and solution grid
     * @param gridSize The horizontal and vertical length of the grid
     * @param numbers The numberlayout of the grid
     */
    public void setGrid(int gridSize, ArrayList<Integer> numbers){
        if(grid == null || grid[0].length != gridSize){
            grid = new NumSquare[gridSize][gridSize];
            solutiongrid = new NumSquare[gridSize][gridSize];
        }
        int correct = 1;
        int index = 0;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int num = numbers.get(index++);
                grid[i][j] = new NumSquare(num,size);
                if(num == 0) emptySquare = new Point(j,i);

                solutiongrid[i][j] = new NumSquare(correct++,size);
            }
        }
        solutiongrid[gridSize-1][gridSize-1] = new NumSquare(0,size);
    }

    @Override
    public SuccessState doAction(Action action) {
        if(!evaluateMove(action)) return SuccessState.FAILURE;
        switch (action){
            case MOVE_LEFT:
                exchange(new Point(0,-1));
                break;
            case MOVE_UP:
                exchange(new Point(-1,0));
                break;
            case MOVE_RIGHT:
                exchange(new Point(0,1));
                break;
            case MOVE_DOWN:
                exchange(new Point(1,0));
                break;
        }
        return evaluateBoard();
    }

    /**
     * If the proposed move is illegal, false will be returned
     * @param action The proposed move
     * @return Boolean result
     */
    private boolean evaluateMove(Action action){
        switch (action){
            case MOVE_LEFT:
                if(emptySquare.y < 1) return false;
                break;
            case MOVE_UP:
                if(emptySquare.x < 1) return false;
                break;
            case MOVE_RIGHT:
                if(emptySquare.y > grid[0].length - 2) return false;
                break;
            case MOVE_DOWN:
                if(emptySquare.x > grid[0].length - 2) return false;
                break;
        }
        return true;
    }

    /**
     * If all squares are in the right place then goal is reached
     * @return SuccessState goal reached or move success
     */
    private SuccessState evaluateBoard(){
        int len = grid[0].length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if(grid[i][j].getNumber() != solutiongrid[i][j].getNumber())
                    return SuccessState.SUCCESS;
            }
        }
        return SuccessState.GOAL_REACHED;
    }

    private void exchange(Point p){
        NumSquare k = grid[emptySquare.x + p.x][emptySquare.y + p.y];
        grid[emptySquare.x + p.x][emptySquare.y + p.y] = grid[emptySquare.x][emptySquare.y];
        grid[emptySquare.x][emptySquare.y] = k;
        emptySquare.x += p.x;
        emptySquare.y += p.y;
    }

    @Override
    public boolean evaluate(Predicate predicate) {
        return false;
    }

    @Override
    public Snapshot createSnapshot() {
        MySnapshot snap = new MySnapshot();
        snap.setGrid(grid);
        snap.setEmptySquare(emptySquare);
        return snap;
    }

    @Override
    public void restoreSnapshot(Snapshot snapshot) {
        MySnapshot snap = (MySnapshot) snapshot;
        grid = snap.getGrid();
        emptySquare = snap.getEmptySquare();
    }

    @Override
    public void paint(Graphics g, Point position) {
        if(position != null) drawPosition = position;
        //Borders
        g.setColor(Color.BLACK);
        int gridSize = grid.length * size;
        g.drawPolygon(
                new Polygon(
                        new int[]{drawPosition.x,drawPosition.x + gridSize,drawPosition.x + gridSize,drawPosition.x},
                        new int[]{drawPosition.y,drawPosition.y,drawPosition.y + gridSize,drawPosition.y + gridSize},
                        4));
        //Grid
        Point pos;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                pos = new Point(drawPosition.x + size * j, drawPosition.y + size * i);
                grid[i][j].draw(pos,g);
            }
        }
    }
}
