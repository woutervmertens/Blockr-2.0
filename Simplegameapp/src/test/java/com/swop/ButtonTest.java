package com.swop;

import org.junit.jupiter.api.BeforeEach;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ButtonTest {
    private Button button;

    @BeforeEach
    void setUp(){
        button = new Button("Turn Left",Color.BLUE,Action.TURN_LEFT);
    }

    @org.junit.jupiter.api.Test
    void setSize() {
        button.setSize(50,50);
        assertTrue(comparePoint(button.getSize(),new Point(50,50)));
    }

    @org.junit.jupiter.api.Test
    void setLocation() {
        button.setLocation(80,60);
        assertTrue(comparePoint(new Point(button.getPolygon().getBounds().x
                ,button.getPolygon().getBounds().y),
                new Point(80,60)));
    }

    @org.junit.jupiter.api.Test
    void isClicked() {
        assertTrue(button.isClicked(85,15));
        assertFalse(button.isClicked(50,50));
    }

    @org.junit.jupiter.api.Test
    void getAction() {
        assertEquals(button.getAction(),Action.TURN_LEFT);
    }

    private boolean comparePoint(Point p1, Point p2){
        return p1.x == p2.x && p1.y == p2.y;
    }
}