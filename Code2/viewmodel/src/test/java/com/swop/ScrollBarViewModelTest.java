package com.swop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ScrollBarViewModelTest {
    ScrollBarViewModel sbvm;

    @BeforeEach
    void setUp() {
        sbvm = new ScrollBarViewModel(new Point(0,0),50,10);
        sbvm.setActive(true);
    }

    @Test
    void handleMousePress() {
    }

    @Test
    void handleMouseRelease() {
    }

    @Test
    void handleMouseDrag() {
    }

    @Test
    void handleReset() {
    }

    @Test
    void getHandlePoly() {
    }

    @Test
    void getHandleColor() {
    }

    @Test
    void getNormalizedYPos() {
    }

    @Test
    void updateHandleHeight() {
    }
}