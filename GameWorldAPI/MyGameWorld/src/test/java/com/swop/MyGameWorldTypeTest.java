package com.swop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class MyGameWorldTypeTest {

    private MyGameWorldType gameWorldType;
    @BeforeEach
    void setUp() {
        gameWorldType = new MyGameWorldType();
    }

    @Test
    void getSupportedActions() {
        assertEquals(gameWorldType.getSupportedActions(), Arrays.asList(MyAction.MOVE_LEFT,MyAction.MOVE_UP,MyAction.MOVE_RIGHT, MyAction.MOVE_DOWN));
    }

    @Test
    void getSupportedPredicates() {
        assertEquals(gameWorldType.getSupportedPredicates(), Collections.emptyList());
    }

    @Test
    void createNumbers() {
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
        assertTrue(numbers.equals(gameWorldType.createNumbers()));
    }

    @Test
    void createNewInstance() {
        MyGameWorld mgw = new MyGameWorld();
        mgw.setGrid(3,gameWorldType.createNumbers());
        MyGameWorld m = (MyGameWorld)gameWorldType.createNewInstance();
        assertNotEquals(m,null);
        assertTrue(compareGrid(mgw.getGrid(),m.getGrid()));
        assertEquals(mgw.getSize(),m.getSize());
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


}