package com.swop.blocks;

import com.swop.Action;
import com.swop.BlockrGame;
import com.swop.GameWorld;
import com.swop.GameWorldType;
import com.swop.command.ExecuteCommand;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class StatementBlockTest {


    GameWorldType gameWorldType;
    private String[] args = {"com.swop.RobotGameWorldType"};
    private int maxBlocks = 10;
    BlockrGame blockrGame = BlockrGame.createInstance(maxBlocks, gameWorldType);
    private GameWorld gameWorld = blockrGame.getGameWorld();
    private Point wPosition = new Point(1, 1);
    private int wWidth = 4;
    private int wHeight = 2;
    private WhileBlock whileBlock = new WhileBlock(wPosition, wWidth, wHeight);
    private Point cPosition = new Point(5, 1);
    private boolean predicate = true;
    private int cWidth = 2;
    private int cHeight = 2;
    private ConditionBlock conditionBlock = new ConditionBlock(cPosition, predicate, cWidth, cHeight);
    private Point aPosition = new Point(2, 2);
    private int aWidth = 3;
    private int aHeight = 2;
    private Action action = Action.MOVE_FORWARD;
    private ActionBlock actionBlock = new ActionBlock(aPosition, aWidth, aHeight, action);
    private Point aPosition1 = new Point(2, 4);
    private int aWidth1 = 3;
    private int aHeight1 = 2;
    private Action action1 = Action.TURN_LEFT;
    private ActionBlock actionBlock1 = new ActionBlock(aPosition1, aWidth1, aHeight1, action1);
    private Point aPosition2 = new Point(2, 6);
    private int aWidth2 = 3;
    private int aHeight2 = 2;
    private Action action2 = Action.TURN_RIGHT;
    private ActionBlock actionBlock2 = new ActionBlock(aPosition2, aWidth2, aHeight2, action2);
    private ExecuteCommand executeCommand = new ExecuteCommand(actionBlock1);
    private int conditionWidth = wWidth / 2;

    {
        try {
            Class<?> clasz = Class.forName(args[0]);
            gameWorldType = (GameWorldType) clasz.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void isConditionValidException() {
        assertThrows(IllegalStateException.class, () -> whileBlock.isConditionValid());

    }


    @Test
    void isConditionValid() {
        gameWorld = gameWorldType.createNewInstance();

        whileBlock.addConditionBlock(conditionBlock);
        assertEquals(conditionBlock, whileBlock.getConditions().get(0), "addConditionBlock failed");
        assertFalse(whileBlock.isConditionValid(), "wallInFront returns true but there is no wall in front");

        //robot turns to the left so there is a wall in front
        blockrGame.executeCommand(executeCommand);
        assertTrue(whileBlock.isConditionValid(), "wallInFront returns false but there is a wall in front");

    }

    @Test
    void execute() {
        // TODO: 15/04/2020 execute is nog niet af
    }

    @Test
    void executeNextBodyBlock() {
        // TODO: 15/04/2020  executeNextBodyBlock is nog niet af
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

}