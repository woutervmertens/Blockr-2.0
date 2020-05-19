package com.swop.handlers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.swop.BlockrGame;
import com.swop.GameWorldType;
import com.swop.blocks.*;
import com.swop.uiElements.UIBlock;
import com.swop.uiElements.UIStatementBlock;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class holding information about all the shared objects between the existing handlers.
 * This class represents the "database" between frontend and backend.
 */
public class SharedData {
    private static SharedData firstInstance = null;
    private final BlockrGame blockrGame;
    private final BiMap<Block, UIBlock> blockUIBlockMap = HashBiMap.create();
    private UIBlock highlightedBlock;

    private SharedData(int maxBlocks, GameWorldType type) {
        blockrGame = new BlockrGame(maxBlocks, type);
    }

    public static SharedData getInstance(int maxBlocks, GameWorldType type) {
        if (firstInstance == null) {
            firstInstance = new SharedData(maxBlocks, type);
        }
        return firstInstance;
    }

    public BlockrGame getBlockrGame() {
        return blockrGame;
    }

    private void putInBlockUIBlockMap(Block key, UIBlock value) {
        if (!blockUIBlockMap.containsKey(key)) {
            blockUIBlockMap.put(key, value);
        }
    }

    public UIBlock getCorrespondingUiBlockFor(Block block) {
        try {
            return blockUIBlockMap.get(block);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Block getCorrespondingBlockFor(UIBlock uiBlock) {
        return blockUIBlockMap.inverse().get(uiBlock);
    }

    public void makeNewCorrespondingBlock(UIBlock uiBlock){
        Block b;
        switch (uiBlock.getType().getType()) {
            case ActionType:
                b = new ActionBlock(uiBlock.getPosition(), uiBlock.getWidth(), uiBlock.getHeight(), uiBlock.getType().getAction());
                break;
            case IfStatement:
                b = new IfBlock(uiBlock.getPosition(), uiBlock.getWidth(), uiBlock.getHeight());
                break;
            case WhileStatement:
                b = new WhileBlock(uiBlock.getPosition(), uiBlock.getWidth(), uiBlock.getHeight());
                break;
            case NotCondition:
                b = new ConditionBlock(uiBlock.getPosition(), false, uiBlock.getWidth(), uiBlock.getHeight(), null);
                break;
            case Predicate:
                b = new ConditionBlock(uiBlock.getPosition(), true, uiBlock.getWidth(), uiBlock.getHeight(), uiBlock.getType().getPredicate());
                break;
            case FunctionCall:
                b = new FunctionCallBlock(uiBlock.getPosition(), uiBlock.getWidth(), uiBlock.getHeight());
                break;
            case FunctionDefinition:
                b = new FunctionDefinitionBlock(uiBlock.getPosition(), uiBlock.getWidth(), uiBlock.getHeight());
                break;
            default:
                throw new IllegalArgumentException("Not a Valid Block Type !");
        }
        putInBlockUIBlockMap(b,uiBlock);
    }

    public void adjustAllBodyBlockGaps() {
        for (Block block : getBlockrGame().getAllBlocksInPA()) {
            if (block instanceof StatementBlock) {
                UIStatementBlock uiStatement = (UIStatementBlock) getCorrespondingUiBlockFor(block);
                uiStatement.setGapSize(((BlockWithBody) block).getGapSize());
            }
        }
    }

    public void adjustAllBlockPositions() {
        for (Block block : getBlockrGame().getAllBlocksInPA()) {
            UIBlock uiBlock = getCorrespondingUiBlockFor(block);
            uiBlock.setPosition(block.getPosition());
        }
    }

    public void setHighlightedBlock(UIBlock block) {
        if (block == null) return;

        if (highlightedBlock != null) highlightedBlock.setHighlightStateOn(false);
        highlightedBlock = block;
        highlightedBlock.setHighlightStateOn(true);
    }

    public Collection<UIBlock> getFunctionDefinitions(){
        return getBlockrGame().getAllBlocksInPA().stream()
                .filter(c -> c instanceof FunctionDefinitionBlock)
                .map(c -> blockUIBlockMap.get(c))
                .collect(Collectors.toList());
    }


    // TODO: find a way to "adjust" highlighted block
}
