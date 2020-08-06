package com.swop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

class GameWorldViewModelTest {

    GameWorldViewModel gwvm;


    @BeforeEach
    void setUp() {
        gwvm = new GameWorldViewModel(new Point(0,0),50,50);
        gwvm.addScrollBar(new ScrollBarViewModel(new Point(40,0),50,10));
        MyGameWorldType gwt = new MyGameWorldType();
        gwvm.setGameWorld(gwt.createNewInstance(),gwt);
    }

    @Test
    void handleMousePress() {
        gwvm.HandleMousePress(1,1);
        gwvm.HandleMousePress(41,1);
        //Nothing happens
    }

    @Test
    void handleMouseRelease() {
        gwvm.HandleMouseRelease(1,1);
        gwvm.HandleMouseRelease(41,1);
        //Nothing happens
    }

    @Test
    void handleMouseDrag() {
        gwvm.HandleMouseDrag(1,1);
        gwvm.HandleMouseDrag(41,1);
        //Nothing happens
    }

    @Test
    void handleReset() {
        Snapshot s = gwvm.getModel().gameWorld.createSnapshot();
        gwvm.HandleReset();
        Snapshot s2 = gwvm.getModel().gameWorld.createSnapshot();
        assertNotEquals(s,s2);
    }
}