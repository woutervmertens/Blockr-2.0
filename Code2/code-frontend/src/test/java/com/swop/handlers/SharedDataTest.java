package com.swop.handlers;

import com.swop.RobotGameWorldType;
import com.swop.blocks.*;
import com.swop.uiElements.BlockType;
import com.swop.uiElements.BlockTypes;
import com.swop.uiElements.UIBlock;
import com.swop.uiElements.UIBlockWithBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SharedDataTest {
    SharedData sharedData;
    @BeforeEach
    void setUp() {
        sharedData = new SharedData(10,new RobotGameWorldType());
    }

    @Test
    void getBlockrGame() {
        assertNotNull(sharedData.getBlockrGame());
    }

    @Test
    void getCorrespondingUiBlockFor() {
        assertNull(sharedData.getCorrespondingUiBlockFor(null));
    }

    @Test
    void getCorrespondingBlockFor() {
        assertNull(sharedData.getCorrespondingBlockFor(null));
    }

    @Test
    void makeNewCorrespondingBlock() {
        BlockTypes data = new BlockTypes("test",1, BlockType.ActionType);
        UIBlock a = data.getNewUIBlock(1,1);
        sharedData.makeNewCorrespondingBlock(a);
        Block b = sharedData.getCorrespondingBlockFor(a);
        assertNotNull(b);
        assertTrue(b instanceof ActionBlock);

        data = new BlockTypes("test",1, BlockType.IfStatement);
        a = data.getNewUIBlock(1,1);
        sharedData.makeNewCorrespondingBlock(a);
        b = sharedData.getCorrespondingBlockFor(a);
        assertNotNull(b);
        assertTrue(b instanceof IfBlock);

        data = new BlockTypes("test",1, BlockType.WhileStatement);
        a = data.getNewUIBlock(1,1);
        sharedData.makeNewCorrespondingBlock(a);
        b = sharedData.getCorrespondingBlockFor(a);
        assertNotNull(b);
        assertTrue(b instanceof WhileBlock);

        data = new BlockTypes("test",1, BlockType.NotCondition);
        a = data.getNewUIBlock(1,1);
        sharedData.makeNewCorrespondingBlock(a);
        b = sharedData.getCorrespondingBlockFor(a);
        assertNotNull(b);
        assertTrue(b instanceof ConditionBlock);

        data = new BlockTypes("test",1, BlockType.Predicate);
        a = data.getNewUIBlock(1,1);
        sharedData.makeNewCorrespondingBlock(a);
        b = sharedData.getCorrespondingBlockFor(a);
        assertNotNull(b);
        assertTrue(b instanceof ConditionBlock);

        data = new BlockTypes("test",1, BlockType.FunctionDefinition);
        a = data.getNewUIBlock(1,1);
        sharedData.makeNewCorrespondingBlock(a);
        b = sharedData.getCorrespondingBlockFor(a);
        assertNotNull(b);
        assertTrue(b instanceof FunctionDefinitionBlock);

        data = new BlockTypes("test",1, BlockType.FunctionCall);
        a = data.getNewUIBlock(1,1);
        sharedData.makeNewCorrespondingBlock(a);
        b = sharedData.getCorrespondingBlockFor(a);
        assertNotNull(b);
        assertTrue(b instanceof FunctionCallBlock);
    }

    @Test
    void adjustAllBodyBlockGaps() {
        BlockTypes data = new BlockTypes("test",1, BlockType.FunctionDefinition);
        UIBlock a = data.getNewUIBlock(1,1);
        sharedData.makeNewCorrespondingBlock(a);
        Block b = sharedData.getCorrespondingBlockFor(a);

        ((BlockWithBody)b).setGapSize(54);
        sharedData.getBlockrGame().dropBlockInPAAt(b,1,1);
        sharedData.adjustAllBodyBlockGaps();
        assertEquals(54,((UIBlockWithBody)a).getGapSize());
    }

    @Test
    void adjustAllBlockPositions() {
        BlockTypes data = new BlockTypes("test",1, BlockType.FunctionDefinition);
        UIBlock a = data.getNewUIBlock(1,1);
        sharedData.makeNewCorrespondingBlock(a);
        Block b = sharedData.getCorrespondingBlockFor(a);

        sharedData.getBlockrGame().dropBlockInPAAt(b,54,1);
        sharedData.adjustAllBlockPositions();
        assertEquals(54,a.getPosition().x);
    }

    @Test
    void handleHighlight() {
        BlockTypes data = new BlockTypes("test",1, BlockType.ActionType);
        UIBlock a = data.getNewUIBlock(1,1);
        sharedData.makeNewCorrespondingBlock(a);
        Block b = sharedData.getCorrespondingBlockFor(a);
        sharedData.getBlockrGame().dropBlockInPAAt(b,1,1);
        assertFalse(a.isHighlight());
        sharedData.handleHighlight();
        assertTrue(a.isHighlight());
    }

    @Test
    void setHighlightedBlock() {
        BlockTypes data = new BlockTypes("test",1, BlockType.ActionType);
        UIBlock a = data.getNewUIBlock(1,1);
        assertFalse(a.isHighlight());
        sharedData.setHighlightedBlock(a);
        assertTrue(a.isHighlight());
        sharedData.setHighlightedBlock(null);
        assertFalse(a.isHighlight());
    }

    @Test
    void getFunctionDefinitions() {
        assertTrue(sharedData.getFunctionDefinitions().isEmpty());
        BlockTypes data = new BlockTypes("test",1, BlockType.FunctionDefinition);
        UIBlock a = data.getNewUIBlock(1,1);
        sharedData.makeNewCorrespondingBlock(a);
        Block b = sharedData.getCorrespondingBlockFor(a);
        sharedData.getBlockrGame().dropBlockInPAAt(b,54,1);
        assertEquals(1,sharedData.getFunctionDefinitions().size());
    }
}