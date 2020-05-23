package com.swop;

import com.swop.blocks.ActionBlock;
import com.swop.blocks.FunctionDefinitionBlock;
import com.swop.blocks.IfBlock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PushBlocksTest {
    Point pos;
    FunctionDefinitionBlock block;
    IfBlock ifBlock;
    ActionBlock action;

    @BeforeEach
    void setUp() {
        pos = new Point(1,1);
        block = new FunctionDefinitionBlock(pos, 1,1);
        ifBlock = new IfBlock(pos,1,1);
        action = new ActionBlock(pos,1,1,RobotAction.TURN_RIGHT);
    }

    @Test
    void pushFrom() {
        ifBlock.insertBodyBlockAtIndex(action,0);
        block.insertBodyBlockAtIndex(ifBlock,0);
        PushBlocks.pushFrom(ifBlock.getBodyBlocks(),0,2);
        assertEquals(1,ifBlock.getPosition().y);
        assertEquals(3,action.getPosition().y);
    }
}