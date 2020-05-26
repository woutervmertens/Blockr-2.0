package com.swop.handlers;

import com.swop.RobotAction;
import com.swop.RobotGameWorld;
import com.swop.RobotGameWorldType;
import com.swop.blocks.ActionBlock;
import com.swop.blocks.Block;
import com.swop.uiElements.BlockType;
import com.swop.uiElements.BlockTypes;
import com.swop.uiElements.UIActionBlock;
import com.swop.uiElements.UIBlock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ExecuteProgramHandlerTest {
    SharedData sharedData;
    ExecuteProgramHandler executeProgramHandler;

    @BeforeEach
    void setUp() {
        sharedData = new SharedData(10, new RobotGameWorldType());
        executeProgramHandler = new ExecuteProgramHandler(sharedData);
    }

    @Test
    void executeNext() {
        Block a = new ActionBlock(new Point(1,1),1,1, RobotAction.MOVE_FORWARD);
        a.setBlockrGame(sharedData.getBlockrGame());
        sharedData.getBlockrGame().dropBlockInPAAt(a,1,1);
        int[] originalPos = ((RobotGameWorld)sharedData.getBlockrGame().getGameWorld()).getRobot().getPosition();
        executeProgramHandler.executeNext();
        assertNotEquals(originalPos[1],((RobotGameWorld)sharedData.getBlockrGame().getGameWorld()).getRobot().getPosition()[1]);
    }

    @Test
    void updateHighlight() {
        BlockTypes data = new BlockTypes("test",1, BlockType.ActionType);
        UIBlock a = data.getNewUIBlock(1,1);
        sharedData.makeNewCorrespondingBlock(a);
        Block b = sharedData.getCorrespondingBlockFor(a);
        sharedData.getBlockrGame().dropBlockInPAAt(b,1,1);
        assertFalse(a.isHighlight());
        executeProgramHandler.updateHighlight();
        assertTrue(a.isHighlight());
    }
}