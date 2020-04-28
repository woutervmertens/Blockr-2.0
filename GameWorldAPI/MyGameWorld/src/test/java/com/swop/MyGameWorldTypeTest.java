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
        assertEquals(gameWorldType.getSupportedActions(), Arrays.asList(MyAction.MOVE_LEFT,MyAction.MOVE_UP,MyAction.MOVE_RIGHT, MyAction.MOVE_DOWN));
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