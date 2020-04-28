package com.swop;

public enum RobotAction implements Action {
    MOVE_FORWARD {
        @Override
        public String toString() {
            return "Move Forward";
        }
    },
    TURN_LEFT {
        @Override
        public String toString() {
            return "Turn Left";
        }
    },
    TURN_RIGHT {
        @Override
        public String toString() {
            return "Turn Right";
        }
    }
}
