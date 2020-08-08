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
        float hp = sbvm.model.getHandleYPosition();
        sbvm.HandleMousePress(1,49);
        sbvm.HandleMousePress(1,49);
        sbvm.HandleMousePress(1,49);
        assertTrue(hp < sbvm.model.getHandleYPosition());
        hp = sbvm.model.getHandleYPosition();
        sbvm.HandleMousePress(1,1);
        sbvm.HandleMousePress(1,1);
        assertTrue(hp > sbvm.model.getHandleYPosition());
    }

    @Test
    void handleMouseDrag() {
        sbvm.updateHandleHeight(50);
        sbvm.HandleMouseDrag(1,50);
        assertTrue(sbvm.model.getHandleYPosition() < 0.1f);
        sbvm.HandleMousePress(1,5);
        sbvm.HandleMouseDrag(1,50);
        assertTrue(sbvm.model.getHandleYPosition() > 0.9f);
        sbvm.HandleMouseRelease(1,50);
        sbvm.HandleMouseDrag(1,1);
        assertTrue(sbvm.model.getHandleYPosition() > 0.9f);
    }

    @Test
    void getHandlePoly() {
        assertNotNull(sbvm.getHandlePoly());
    }

    @Test
    void getHandleColor() {
        assertEquals(Color.GRAY,sbvm.getHandleColor());
    }

    @Test
    void updateHandleHeight() {
        //(2/3) of original height (50) / given height
        sbvm.updateHandleHeight(50);
        assertEquals(33,sbvm.model.getHandleHeight());
        sbvm.updateHandleHeight(100);
        assertEquals(16,sbvm.model.getHandleHeight());
    }

    @Test
    void getBGPoly() {
        assertNotNull(sbvm.getBGPoly());
    }

    @Test
    void getBGColor() {
        assertEquals(Color.lightGray,sbvm.getBGColor());
    }

    @Test
    void handleNewMaxHeight() {
        sbvm.model.setHandleYPosition(1.0f);
        sbvm.handleNewMaxHeight(50,100);
        float f = sbvm.model.getHandleYPosition();
        assertTrue(f < 0.6f && f > 0.4f);
        sbvm.handleNewMaxHeight(50,100);
        f = sbvm.model.getHandleYPosition();
        assertTrue(f < 0.26f && f > 0.24f);
    }
}