package com.swop;

import com.swop.blocks.ActionBlock;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ProgramAreaTest {

    private ProgramArea programArea = new ProgramArea();

    private Point aPosition = new Point(2,2);
    private int aWidth = 3;
    private int aHeight = 2;
    private Action action = Action.MOVE_FORWARD;

    private ActionBlock actionBlock = new ActionBlock(aPosition,aWidth,aHeight,action);



    @Test
    void dropBlock() {
        // BIG method
    }

    @Test
    void setNextCurrentBlock() {
    }

    @Test
    void getBlockAt() {
        // right position
        //false position
    }

    @Test
    void getConnectionPoint() {
    }

    @Test
    void addProgramBlockAfter() {
    }

    @Test
    void removeProgramBlock() {
    }

    @Test
    void removeBlockFromPA() {
    }

    @Test
    void reset() {
    }
}