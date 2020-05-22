package com.swop.blocks;

import com.swop.*;
import com.swop.command.ExecuteCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class StatementBlockTest {


    GameWorldType gameWorldType;
    private String[] args = {"com.swop.RobotGameWorldType"};
    private int maxBlocks;
    BlockrGame blockrGame;
    private GameWorld gameWorld;
    private Point wPosition;
    private int wWidth;
    private int wHeight;
    private WhileBlock whileBlock;
    private Point cPosition;
    private boolean predicate;
    private int cWidth;
    private int cHeight;
    private ConditionBlock conditionBlock;
    private Point aPosition;
    private int aWidth;
    private int aHeight;
    private Action action;
    private ActionBlock actionBlock;
    private Point aPosition1;
    private int aWidth1;
    private int aHeight1;
    private Action action1;
    private ActionBlock actionBlock1;
    private Point aPosition2;
    private int aWidth2;
    private int aHeight2;
    private Action action2;
    private ActionBlock actionBlock2;
    private ExecuteCommand executeCommand;
    private int conditionWidth;

    {
        try {
            Class<?> clasz = Class.forName(args[0]);
            gameWorldType = (GameWorldType) clasz.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp(){
        gameWorldType = new RobotGameWorldType();
        maxBlocks = 10;
        blockrGame = new BlockrGame(maxBlocks, gameWorldType);
        gameWorld = blockrGame.getGameWorld();
        wPosition = new Point(1, 1);
        wWidth = 4;
        wHeight = 2;
        whileBlock = new WhileBlock(wPosition, wWidth, wHeight);
        whileBlock.setBlockrGame(blockrGame);
        cPosition = new Point(5, 1);
        predicate = true;
        cWidth = 2;
        cHeight = 2;
        conditionBlock = new ConditionBlock(cPosition, predicate, cWidth, cHeight, RobotPredicate.WALL_IN_FRONT);
        conditionBlock.setBlockrGame(blockrGame);
        aPosition = new Point(2, 2);
        aWidth = 3;
        aHeight = 2;
        action = RobotAction.MOVE_FORWARD;
        actionBlock = new ActionBlock(aPosition, aWidth, aHeight, action);
        actionBlock.setBlockrGame(blockrGame);
        aPosition1 = new Point(2, 4);
        aWidth1 = 3;
        aHeight1 = 2;
        action1 = RobotAction.TURN_LEFT;
        actionBlock1 = new ActionBlock(aPosition1, aWidth1, aHeight1, action1);
        actionBlock1.setBlockrGame(blockrGame);
        aPosition2 = new Point(2, 6);
        aWidth2 = 3;
        aHeight2 = 2;
        action2 = RobotAction.TURN_RIGHT;
        actionBlock2 = new ActionBlock(aPosition2, aWidth2, aHeight2, action2);
        actionBlock2.setBlockrGame(blockrGame);
        executeCommand = new ExecuteCommand(gameWorld,actionBlock1);
        conditionWidth = wWidth / 2;
    }

    @Test
    void isConditionValidException() {
        whileBlock.addConditionBlock(conditionBlock);
        whileBlock.addConditionBlock(new ConditionBlock(cPosition,false,cWidth,cHeight,null));
        assertThrows(IllegalStateException.class, () -> whileBlock.isConditionValid());
    }


    @Test
    void isConditionValid() {
        whileBlock.addConditionBlock(new ConditionBlock(cPosition,false,cWidth,cHeight,null));
        assertFalse(whileBlock.isConditionValid());
        whileBlock.conditions.clear();

        whileBlock.addConditionBlock(conditionBlock);
        assertEquals(conditionBlock, whileBlock.getConditions().get(0), "addConditionBlock failed");
        assertFalse(whileBlock.isConditionValid(), "wallInFront returns true but there is no wall in front");

        //robot turns to the left so there is a wall in front
        blockrGame.executeCommand(executeCommand);
        assertTrue(whileBlock.isConditionValid(), "wallInFront returns false but there is a wall in front");
    }

    @Test
    void addBeforeAfterInsertAndRemoveBodyBlock() {
        whileBlock.insertBodyBlockAtIndex(actionBlock, 0);
        assertEquals(whileBlock.getBodyBlocks().get(0), actionBlock, "actionBlock isn't first");
        whileBlock.addBodyBlockBefore(actionBlock1, actionBlock);
        assertEquals(whileBlock.getBodyBlocks().get(0), actionBlock1, "actionBlock1 isn't first");
        assertEquals(whileBlock.getBodyBlocks().get(1), actionBlock, "actionBlock isn't second");
        whileBlock.removeBodyBlock(actionBlock1);
        assertFalse(whileBlock.getBodyBlocks().contains(actionBlock1), "actionBlock1 isn't removed");
        assertEquals(whileBlock.getBodyBlocks().get(0), actionBlock, "actionBlock isn't first");
        whileBlock.addBodyBlockAfter(actionBlock1, actionBlock);
        assertEquals(whileBlock.getBodyBlocks().get(0), actionBlock, "actionBlock isn't first");
        assertEquals(whileBlock.getBodyBlocks().get(1), actionBlock1, "actionBlock1 isn't second");

        whileBlock.insertBodyBlockAtIndex(actionBlock2, 1);
        assertEquals(whileBlock.getBodyBlocks().get(0), actionBlock, "actionBlock isn't first");
        assertEquals(whileBlock.getBodyBlocks().get(1), actionBlock2, "actionBlock2 isn't second");
        assertEquals(whileBlock.getBodyBlocks().get(2), actionBlock1, "actionBlock1 isn't third");
    }


    @Test
    void getPlugPosition() {
        Point expectedPosition = new Point(wPosition.x, wPosition.y + wHeight + whileBlock.getGapSize() + whileBlock.getStep());
        assertEquals(expectedPosition.x, whileBlock.getPlugPosition().x, "The expected and actual x-value of the plugPosition are different");
        assertEquals(expectedPosition.y, whileBlock.getPlugPosition().y, "The expected and actual y-value of the plugPosition are different");
    }

    @Test
    void getSocketPosition() {
        Point expectedPosition = new Point(wPosition.x, wPosition.y + whileBlock.getStep());
        assertEquals(expectedPosition.x, whileBlock.getSocketPosition().x, "The expected and actual x-value of the socketPosition are different");
        assertEquals(expectedPosition.y, whileBlock.getSocketPosition().y, "The expected and actual y-value of the socketPosition are different");
    }

    @Test
    void getBodyPlugPosition() {
        int pillarWidth = 10;
        Point expectedPosition = new Point(wPosition.x + pillarWidth, wPosition.y + wHeight - whileBlock.getStep());
        assertEquals(expectedPosition.x, whileBlock.getBodyPlugPosition().x, "The expected and actual x-value of the bodyPlugPosition are different");
        assertEquals(expectedPosition.y, whileBlock.getBodyPlugPosition().y, "The expected and actual y-value of the bodyPlugPosition are different");
    }

    @Test
    void getConditionPlugPosition() {
        Point expectedPosition = new Point(wPosition.x + conditionWidth + whileBlock.getStep(), wPosition.y);
        assertEquals(expectedPosition.x, whileBlock.getConditionPlugPosition().x, "The expected and actual x-value of the conditionPlugPosition are different");
        assertEquals(expectedPosition.y, whileBlock.getConditionPlugPosition().y, "The expected and actual y-value of the conditionPlugPosition are different");
    }

    @Test
    void increaseAndDecreaseGapSize() {
        whileBlock.setGapSize(2);
        assertEquals(2, whileBlock.getGapSize(), "gapsize isn't 2");

        whileBlock.increaseGapSize(10);
        assertEquals(12, whileBlock.getGapSize(), "gapsize isn't 12");

        whileBlock.increaseGapSize(-8);
        assertEquals(4, whileBlock.getGapSize(), "gapsize isn't 4");

    }

    @Test
    void setPreviousDropPosition() {
        whileBlock.addConditionBlock(conditionBlock);
        whileBlock.insertBodyBlockAtIndex(actionBlock,0);
        whileBlock.setPreviousDropPosition(new Point(99,99));
        assertEquals(99,whileBlock.getPreviousDropPosition().x);
        assertEquals(99,whileBlock.getPreviousDropPosition().y);

        assertEquals(cPosition,whileBlock.conditions.get(0).getPreviousDropPosition());

        assertEquals(aPosition,whileBlock.bodyBlocks.get(0).getPreviousDropPosition());
    }

    @Test
    void execute() {
        whileBlock.execute();
        assertFalse(whileBlock.isBusy());
        whileBlock.addConditionBlock(new ConditionBlock(cPosition,false,cWidth,cHeight,null));
        whileBlock.addConditionBlock(conditionBlock);
        whileBlock.insertBodyBlockAtIndex(actionBlock,0);
        whileBlock.insertBodyBlockAtIndex(actionBlock1,1);
        whileBlock.execute();
        assertTrue(whileBlock.isBusy());
        whileBlock.execute();
        assertTrue(whileBlock.isBusy());
    }

    @Test
    void removeConditionBlock() {
        whileBlock.addConditionBlock(conditionBlock);
        whileBlock.removeConditionBlock(conditionBlock);
        assertTrue(whileBlock.conditions.isEmpty());
    }

    @Test
    void isPositionOn() {
        assertTrue(whileBlock.isPositionOn(wPosition.x + 1,wPosition.y + 1));
        assertFalse(whileBlock.isPositionOn(wPosition.x - 1,wPosition.y - 1));
    }

    @Test
    void getCount() {
        assertEquals(1,whileBlock.getCount());
        whileBlock.addConditionBlock(conditionBlock);
        assertEquals(2,whileBlock.getCount());
    }
}