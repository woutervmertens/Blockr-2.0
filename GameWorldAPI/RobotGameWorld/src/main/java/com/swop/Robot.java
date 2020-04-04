package com.swop;

public class Robot {
    private int[] position;

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    private Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //HELPERS
    public void turnLeft(){
        direction = direction.NeighbourLeft();
    }

    public void turnRight(){
        direction = direction.NeighbourRight();
    }

    public int[] getPosititionInFront(){
        int[] pif = new int[]{0,0};
        switch (direction){
            case UP:
                pif[0] = position[0] - 1;
                pif[1] = position[1];
                break;
            case RIGHT:
                pif[0] = position[0];
                pif[1] = position[1] + 1;
                break;
            case DOWN:
                pif[0] = position[0] + 1;
                pif[1] = position[1];
                break;
            case LEFT:
                pif[0] = position[0];
                pif[1] = position[1] - 1;
                break;
        }
        return pif;
    }


}
