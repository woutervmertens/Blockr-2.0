package com.swop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotPredicateTest {
    @Test
    void RobotPredicate(){
        RobotPredicate r = RobotPredicate.WALL_IN_FRONT;
        assertEquals("WIF",r.toString());
    }

}