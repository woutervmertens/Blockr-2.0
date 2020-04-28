package com.swop;

public enum RobotPredicate implements Predicate{
    WALL_IN_FRONT {
        @Override
        public String toString() {
            return "WIF";
        }
    }
}
