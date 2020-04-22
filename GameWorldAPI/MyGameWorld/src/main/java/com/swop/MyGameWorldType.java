package com.swop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MyGameWorldType implements GameWorldType{
    @Override
    public Collection getSupportedActions() {
        return Arrays.asList(Action.MOVE_LEFT,Action.MOVE_UP,Action.MOVE_RIGHT, Action.MOVE_DOWN);
    }

    @Override
    public Collection getSupportedPredicates() {
        return Collections.emptyList();
    }

    @Override
    public GameWorld createNewInstance() {

        MyGameWorld res = new MyGameWorld();
        res.setGrid(3,createNumbers());
        return res;
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
}
