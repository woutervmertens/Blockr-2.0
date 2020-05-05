package com.swop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void neighbourLeft() {
        Direction dir = Direction.RIGHT;
        assertEquals(Direction.UP, dir.NeighbourLeft());
        dir = Direction.UP;
        assertEquals(Direction.LEFT,dir.NeighbourLeft());
    }

    @Test
    void neighbourRight() {
        Direction dir = Direction.RIGHT;
        assertEquals(Direction.DOWN,dir.NeighbourRight());
        dir = Direction.LEFT;
        assertEquals(Direction.UP,dir.NeighbourRight());
    }
}