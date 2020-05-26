package com.swop.handlers;

import com.swop.RobotGameWorldType;
import com.swop.blocks.Block;
import com.swop.uiElements.BlockType;
import com.swop.uiElements.BlockTypes;
import com.swop.uiElements.UIBlock;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

class DisplaceBlockHandlerTest {
    SharedData sharedData;
    DisplaceBlockHandler displaceBlockHandler;

    @BeforeEach
    void setUp(){
        sharedData = new SharedData(10,new RobotGameWorldType());
        displaceBlockHandler = new DisplaceBlockHandler(sharedData);
    }

    @org.junit.jupiter.api.Test
    void handleReleaseInPAAt() {
        BlockTypes data = new BlockTypes("test",1, BlockType.ActionType);
        UIBlock a = data.getNewUIBlock(1,1);
        displaceBlockHandler.handleReleaseInPAAt(a,1,1);
        assertTrue(a.isHighlight());
    }

    @org.junit.jupiter.api.Test
    void handleReleaseOutsidePA() {
        BlockTypes data = new BlockTypes("test",1, BlockType.FunctionDefinition);
        UIBlock a = data.getNewUIBlock(1,1);
        sharedData.makeNewCorrespondingBlock(a);
        Block b = sharedData.getCorrespondingBlockFor(a);
        sharedData.getBlockrGame().dropBlockInPAAt(b,1,1);
        displaceBlockHandler.handleReleaseOutsidePA(a);
        assertFalse(a.isHighlight());
    }

    @org.junit.jupiter.api.Test
    void getAllUIBlocksInPA() {
        assertTrue(displaceBlockHandler.getAllUIBlocksInPA().isEmpty());
        BlockTypes data = new BlockTypes("test",1, BlockType.ActionType);
        UIBlock a = data.getNewUIBlock(1,1);
        sharedData.makeNewCorrespondingBlock(a);
        Block b = sharedData.getCorrespondingBlockFor(a);
        sharedData.getBlockrGame().dropBlockInPAAt(b,1,1);
        assertEquals(1,displaceBlockHandler.getAllUIBlocksInPA().size());

    }

    @org.junit.jupiter.api.Test
    void handleClickOn() {
        BlockTypes data = new BlockTypes("test",1, BlockType.ActionType);
        UIBlock a = data.getNewUIBlock(1,1);
        sharedData.makeNewCorrespondingBlock(a);
        Block b = sharedData.getCorrespondingBlockFor(a);
        sharedData.getBlockrGame().dropBlockInPAAt(b,1,1);
        displaceBlockHandler.handleClickOn(a);
        assertTrue(displaceBlockHandler.getAllUIBlocksInPA().isEmpty());
    }
}