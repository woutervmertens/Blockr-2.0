package com.swop;

public enum Action {
    MOVE_FORWARD("Move forward"),
    TURN_LEFT("Turn left"),
    TURN_RIGHT("Turn right"),
    MOVE_LEFT("Move left"),
    MOVE_UP("Move up"),
    MOVE_RIGHT("Move right"),
    MOVE_DOWN("Move down");

    private String title;
    private Action(String title){
        this.title = title;
    }
    @Override
    public String toString() {
        return title;
    }
}
