package com.swop;

public class RobotSnapshot implements Snapshot {
    private Robot robot = new Robot();

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot.setPosition(robot.getPosition());
        this.robot.setDirection(robot.getDirection());
    }

    private Square[][] grid;

    public Square[][] getGrid() {
        return grid;
    }

    public void setGrid(Square[][] grid) {
        this.grid = grid.clone();
    }
}
