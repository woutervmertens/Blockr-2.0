package com.swop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepaintEventControllerTest {

    RepaintEventController rec;
    @BeforeEach
    void setUp() {
        rec = RepaintEventController.getInstance();
    }

    @Test
    void callRepaint() {
        rec.CallRepaint();
        assertTrue(rec.ShouldRepaint());
    }

    @Test
    void shouldRepaint() {
        rec.CallRepaint();
        assertTrue(rec.ShouldRepaint());
        assertFalse(rec.ShouldRepaint());
    }
}