package com.swop;

public enum Predicate {
    WALL_IN_FRONT("WIF");
    private String title;
    private Predicate(String title){
        this.title = title;
    }
    @Override
    public String toString() {
        return title;
    }
}
