package com.swop;

public enum MyAction implements Action{
    MOVE_LEFT{
        @Override
        public String toString() { return "Move left";}},
    MOVE_UP{
        @Override
        public String toString() { return "Move up";}},
    MOVE_RIGHT{
        @Override
        public String toString() { return "Move right";}},
    MOVE_DOWN{
        @Override
        public String toString() { return "Move down";}}
}
