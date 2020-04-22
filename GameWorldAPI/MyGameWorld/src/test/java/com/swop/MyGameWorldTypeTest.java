package com.swop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class MyGameWorldTypeTest {

    private MyGameWorldType gameWorldType;
    @BeforeEach
    void setUp() {
        gameWorldType = new MyGameWorldType();
    }

    @Test
    void getSupportedActions() {
        assertEquals(gameWorldType.getSupportedActions(), Arrays.asList(Action.MOVE_LEFT,Action.MOVE_UP,Action.MOVE_RIGHT, Action.MOVE_DOWN));
    }

    @Test
    void getSupportedPredicates() {
        assertEquals(gameWorldType.getSupportedPredicates(), Collections.emptyList());
    }

    @Test
    void createNewInstance() {
        assertNotEquals(gameWorldType.createNewInstance(),null);
    }
}