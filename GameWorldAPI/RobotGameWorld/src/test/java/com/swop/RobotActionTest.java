package com.swop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotActionTest {
    @Test
    void RobotAction(){
        RobotAction r = RobotAction.MOVE_FORWARD;
        assertEquals("Move Forward",r.toString());
        r = RobotAction.TURN_LEFT;
        assertEquals("Turn Left",r.toString());
        r = RobotAction.TURN_RIGHT;
        assertEquals("Turn Right",r.toString());
    }

}