package com.swop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MyGameWorldTest {
    private MyGameWorld gameWorld;
    private ArrayList<Integer> numbers;

    @BeforeEach
    void setUp(){
        gameWorld = new MyGameWorld();
        numbers = createNumbers();
    }

    @Test
    void actions()
    {
        MyAction a = MyAction.MOVE_RIGHT;
        assertEquals("Move right",a.toString());
        a = MyAction.MOVE_LEFT;
        assertEquals("Move left",a.toString());
        a = MyAction.MOVE_UP;
        assertEquals("Move up",a.toString());
        a = MyAction.MOVE_DOWN;
        assertEquals("Move down",a.toString());
    }

    private ArrayList<Integer> createNumbers(){
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(0);
        numbers.add(1);
        numbers.add(3);
        numbers.add(4);
        numbers.add(2);
        numbers.add(5);
        numbers.add(7);
        numbers.add(8);
        numbers.add(6);
        return numbers;
    }

    @org.junit.jupiter.api.Test
    void setSize() {
        gameWorld.setSize(25);
        assertEquals(gameWorld.getSize(), 25);
    }

    @org.junit.jupiter.api.Test
    void setDrawPosition() {
        gameWorld.setDrawPosition(new Point(200,0));
        assertEquals(gameWorld.getDrawPosition(), new Point(200,0));
    }

    @org.junit.jupiter.api.Test
    void setGrid() {
        NumSquare[][] grid = new NumSquare[4][4];
        numbers = new ArrayList<Integer>();
        for (int i = 0; i < 16; i++) {
            numbers.add(i);
        }
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = numbers.get(index++);
                grid[i][j] = new NumSquare(num, 50);
            }
        }
        gameWorld.setGrid(4,numbers);
        assertTrue(compareGrid(gameWorld.getGrid(),grid));
    }

    private boolean compareGrid(NumSquare[][] grid1, NumSquare[][] grid2){
        if(grid1.length != grid2.length || grid1[0].length != grid2[0].length) return false;
        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[0].length; j++) {
                if(grid1[i][j].getNumber() != grid2[i][j].getNumber()) return false;
            }
        }
        return true;
    }

    @org.junit.jupiter.api.Test
    void doAction() {
        NumSquare[][] grid = new NumSquare[3][3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = numbers.get(index++);
                grid[i][j] = new NumSquare(num, 50);
            }
        }
        gameWorld.setGrid(3,numbers);
        //Test illegal action
        assertEquals(SuccessState.FAILURE,gameWorld.doAction(MyAction.MOVE_LEFT));
        assertEquals(SuccessState.FAILURE,gameWorld.doAction(MyAction.MOVE_UP));
        //Test legal action
        assertEquals(SuccessState.SUCCESS,gameWorld.doAction(MyAction.MOVE_RIGHT));
        //Test moved
        assertFalse(compareGrid(grid,gameWorld.getGrid()));
        //Test goal reached
        gameWorld.doAction(MyAction.MOVE_DOWN);
        gameWorld.doAction(MyAction.MOVE_RIGHT);
        assertEquals(SuccessState.GOAL_REACHED,gameWorld.doAction(MyAction.MOVE_DOWN));
        //Test no overflow
        assertEquals(SuccessState.FAILURE,gameWorld.doAction(MyAction.MOVE_RIGHT));
        //Test actions after overflow
        assertEquals(SuccessState.SUCCESS,gameWorld.doAction(MyAction.MOVE_LEFT));
        assertEquals(SuccessState.SUCCESS,gameWorld.doAction(MyAction.MOVE_UP));
    }

    @org.junit.jupiter.api.Test
    void createSnapshot() {
        MySnapshot snap;
        NumSquare[][] grid = new NumSquare[3][3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = numbers.get(index++);
                grid[i][j] = new NumSquare(num, 50);
            }
        }
        gameWorld.setGrid(3,numbers);
        snap = (MySnapshot) gameWorld.createSnapshot();
        assertTrue(compareGrid(snap.getGrid(),grid));
        assertTrue(comparePoint(snap.getEmptySquare(),new Point(0,0)));
    }

    @Test
    void evaluate()
    {
        assertFalse(gameWorld.evaluate(null));
    }

    @org.junit.jupiter.api.Test
    void restoreSnapshot() {
        MySnapshot snap = new MySnapshot();
        NumSquare[][] grid = new NumSquare[3][3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = numbers.get(index++);
                grid[i][j] = new NumSquare(num, 50);
            }
        }
        Point emptySquare = new Point(0,0);
        snap.setGrid(grid);
        snap.setEmptySquare(emptySquare);
        gameWorld.restoreSnapshot(snap);
        assertTrue(compareGrid(gameWorld.getGrid(),grid));
        assertTrue(comparePoint(gameWorld.getEmptySquare(),emptySquare));
    }
    private boolean comparePoint(Point p1, Point p2){
        return p1.x == p2.x && p2.y == p2.y;
    }
}