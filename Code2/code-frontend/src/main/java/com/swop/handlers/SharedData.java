package com.swop.handlers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.swop.BlockrGame;
import com.swop.GameWorldType;
import com.swop.blocks.*;
import com.swop.uiElements.BlockType;
import com.swop.uiElements.UIBlock;
import com.swop.uiElements.UIBlockWithBody;

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

    /**
     * Adds a new element to the Block-UIBlock BiMap.
     * @param key A Block.
     * @param value A UIBlock.
     */
    private void putInBlockUIBlockMap(Block key, UIBlock value) {
        if (!blockUIBlockMap.containsKey(key)) {
            blockUIBlockMap.put(key, value);
        }
    }

    /**
     * Gets the corresponding UIBlock for the given Block.
     * @param block The Block.
     * @return The corresponding UIBlock.
     */
    public UIBlock getCorrespondingUiBlockFor(Block block) {
        try {
            return blockUIBlockMap.get(block);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Gets the corresponding Block for the given UIBlock.
     * @param uiBlock The UIBlock.
     * @return The corresponding Block.
     */
    public Block getCorrespondingBlockFor(UIBlock uiBlock) {
        return blockUIBlockMap.inverse().get(uiBlock);
    }

    /**
     * Gets the corresponding FunctionDefinitionBlock for the given UIBlock.
     * @param uiBlock The UIBlock.
     * @return The corresponding FunctionDefinitionBlock.
     */
    private FunctionDefinitionBlock getCorrespondingDefinition(UIBlock uiBlock){
        for (UIBlock b : blockUIBlockMap.values()) {
            if(b.getType().getType() == BlockType.FunctionDefinition
                    && b.getText() == uiBlock.getText()){
                return (FunctionDefinitionBlock)getCorrespondingBlockFor(b);
            }
        }
        throw new IllegalStateException("No matching definition was found");
    }

    /**
     * Creates a new corresponding Block for a given UIBlock.
     * @param uiBlock A UIBlock.
     */
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
                b = new FunctionCallBlock(uiBlock.getPosition(), uiBlock.getWidth(), uiBlock.getHeight(), getCorrespondingDefinition(uiBlock));
                break;
            case FunctionDefinition:
                b = new FunctionDefinitionBlock(uiBlock.getPosition(), uiBlock.getWidth(), uiBlock.getHeight());
                break;
            default:
                throw new IllegalArgumentException("Not a Valid Block Type !");
        }
        putInBlockUIBlockMap(b,uiBlock);
    }

    /**
     * Makes sure all the gaps in the UIBlocks with a body line up with the size of the body of their corresponding Blocks.
     */
    public void adjustAllBodyBlockGaps() {
        for (Block block : getBlockrGame().getAllBlocksInPA()) {
            if (block instanceof BlockWithBody) {
                UIBlockWithBody uiBlockWithBody = (UIBlockWithBody) getCorrespondingUiBlockFor(block);
                uiBlockWithBody.setGapSize(((BlockWithBody) block).getGapSize());
            }
        }
    }

    /**
     * Makes sure all UIBlocks are in the correct positions, as decided in their corresponding Blocks.
     */
    public void adjustAllBlockPositions() {
        for (Block block : getBlockrGame().getAllBlocksInPA()) {
            UIBlock uiBlock = getCorrespondingUiBlockFor(block);
            uiBlock.setPosition(block.getPosition());
        }
    }

    /**
     * Calls @setHighlightedBlock() on the next to be executed UIBlock.
     */
    public void handleHighlight(){
        //setHighlightedBlock(getCorrespondingUiBlockFor(blockrGame.getProgramArea().getNextProgramBlock()));
        setHighlightedBlock(getCorrespondingUiBlockFor(blockrGame.getToHighlightBlock()));
    }

    /**
     * Removes the highlight of the previously highlighted UIBlock and turns on the highlight for the given UIBlock.
     * @param block The to-be-highlighted UIBlock.
     */
    public void setHighlightedBlock(UIBlock block) {
        if (highlightedBlock != null) highlightedBlock.setHighlightStateOn(false);
        if (block == null) return;


        highlightedBlock = block;
        highlightedBlock.setHighlightStateOn(true);
    }

    /**
     * Gets a collection of all the FunctionDefinitionBlocks in the Program Area.
     * @return A Collection of UIBlocks.
     */
    public Collection<UIBlock> getFunctionDefinitions(){
        return getBlockrGame().getAllBlocksInPA().stream()
                .filter(c -> c instanceof FunctionDefinitionBlock)
                .map(c -> blockUIBlockMap.get(c))
                .collect(Collectors.toList());
    }


    // TODO: find a way to "adjust" highlighted block
}
