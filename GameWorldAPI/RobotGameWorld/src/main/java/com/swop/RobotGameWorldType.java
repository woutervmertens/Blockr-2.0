package com.swop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class RobotGameWorldType implements GameWorldType{

    @Override
    public Collection getSupportedActions() {
        return Arrays.asList(Action.MOVE_FORWARD,Action.TURN_LEFT,Action.TURN_RIGHT);
    }

    @Override
    public Collection getSupportedPredicates() {
        return Arrays.asList(Predicate.WALL_IN_FRONT);
    }

    @Override
    public GameWorld createNewInstance() {
        RobotGameWorld res = new RobotGameWorld();
        ArrayList<int[]> walls = createWalls();
        res.setGrid(5,walls,new int[]{2,0});
        res.setRobot(Direction.RIGHT,new int[]{2,1});
        return res;
    }

    private ArrayList<int[]> createWalls(){
        ArrayList<int[]> walls = new ArrayList<int[]>();
        walls.add(new int[]{0,0});
        walls.add(new int[]{0,4});
        walls.add(new int[]{4,0});
        walls.add(new int[]{4,2});
        walls.add(new int[]{2,4});
        walls.add(new int[]{4,3});
        walls.add(new int[]{3,3});
        walls.add(new int[]{1,0});
        walls.add(new int[]{1,4});
        walls.add(new int[]{0,3});
        walls.add(new int[]{1,1});
        return walls;
    }

}
