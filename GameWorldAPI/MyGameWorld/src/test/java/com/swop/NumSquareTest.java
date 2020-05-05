package com.swop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class NumSquareTest {
    NumSquare ns;

    @BeforeEach
    void setUp() {
        ns = new NumSquare(5,10);
    }

    @Test
    void constr(){
        assertEquals(5,ns.getNumber());
        Polygon p = ns.getPolygon();
        assertEquals(4,p.npoints);
        int[] x = {1,9,9,1};
        assertArrayEquals(x,p.xpoints);
        int[] y = {1,1,9,9};
        assertArrayEquals(y,p.ypoints);
    }
}