package com.swop.handlers;



import java.util.*;
import java.util.stream.Collectors;

/**
 * Class holding information about all the shared objects between the existing handlers.
 * This class represents the "database" between frontend and backend.
 */
public class SharedData {
    //private final BlockrGame blockrGame;
    //private final BiMap<BlockModel, UIBlock> blockUIBlockMap = HashBiMap.create();
    //private UIBlock highlightedBlock;
//
    //public SharedData(int maxBlocks, GameWorldType type) {
    //    blockrGame = new BlockrGame(maxBlocks, type);
    //}
//
    //public BlockrGame getBlockrGame() {
    //    return blockrGame;
    //}
//
    ///**
    // * Adds a new element to the Block-UIBlock BiMap.
    // * @param key A Block.
    // * @param value A UIBlock.
    // */
    //private void putInBlockUIBlockMap(BlockModel key, UIBlock value) {
    //    if (!blockUIBlockMap.containsKey(key)) {
    //        blockUIBlockMap.put(key, value);
    //    }
    //}
//
    ///**
    // * Gets the corresponding UIBlock for the given Block.
    // * @param blockModel The Block.
    // * @return The corresponding UIBlock.
    // */
    //public UIBlock getCorrespondingUiBlockFor(BlockModel blockModel) {
    //    try {
    //        return blockUIBlockMap.get(blockModel);
    //    } catch (NullPointerException e) {
    //        return null;
    //    }
    //}
//
    ///**
    // * Gets the corresponding Block for the given UIBlock.
    // * @param uiBlock The UIBlock.
    // * @return The corresponding Block.
    // */
    //public BlockModel getCorrespondingBlockFor(UIBlock uiBlock) {
    //    if(uiBlock == null) return null;
    //    return blockUIBlockMap.inverse().get(uiBlock);
    //}
//
    ///**
    // * Gets the corresponding FunctionDefinitionBlock for the given UIBlock.
    // * @param uiBlock The UIBlock.
    // * @return The corresponding FunctionDefinitionBlock.
    // */
    //private FunctionDefinitionBlockModel getCorrespondingDefinition(UIBlock uiBlock){
    //    for (UIBlock b : blockUIBlockMap.values()) {
    //        if(b.getType().getType() == BlockType.FunctionDefinition
    //                && b.getText() == uiBlock.getText()){
    //            return (FunctionDefinitionBlockModel)getCorrespondingBlockFor(b);
    //        }
    //    }
    //    throw new IllegalStateException("No matching definition was found");
    //}
//
    ///**
    // * Creates a new corresponding Block for a given UIBlock.
    // * @param uiBlock A UIBlock.
    // */
    //public void makeNewCorrespondingBlock(UIBlock uiBlock){
    //    BlockModel b;
    //    switch (uiBlock.getType().getType()) {
    //        case ActionType:
    //            b = new ActionBlockModel(uiBlock.getPosition(), uiBlock.getWidth(), uiBlock.getHeight(), uiBlock.getType().getAction());
    //            break;
    //        case IfStatement:
    //            b = new IfBlockModel(uiBlock.getPosition(), uiBlock.getWidth(), uiBlock.getHeight());
    //            break;
    //        case WhileStatement:
    //            b = new WhileBlockModel(uiBlock.getPosition(), uiBlock.getWidth(), uiBlock.getHeight());
    //            break;
    //        case NotCondition:
    //            b = new ConditionBlockModel(uiBlock.getPosition(), false, uiBlock.getWidth(), uiBlock.getHeight(), null);
    //            break;
    //        case Predicate:
    //            b = new ConditionBlockModel(uiBlock.getPosition(), true, uiBlock.getWidth(), uiBlock.getHeight(), uiBlock.getType().getPredicate());
    //            break;
    //        case FunctionCall:
    //            b = new FunctionCallBlockModel(uiBlock.getPosition(), uiBlock.getWidth(), uiBlock.getHeight(), getCorrespondingDefinition(uiBlock));
    //            break;
    //        case FunctionDefinition:
    //            b = new FunctionDefinitionBlockModel(uiBlock.getPosition(), uiBlock.getWidth(), uiBlock.getHeight());
    //            break;
    //        default:
    //            throw new IllegalArgumentException("Not a Valid Block Type !");
    //    }
    //    putInBlockUIBlockMap(b,uiBlock);
    //}
//
    ///**
    // * Makes sure all the gaps in the UIBlocks with a body line up with the size of the body of their corresponding Blocks.
    // */
    //public void adjustAllBodyBlockGaps() {
    //    for (BlockModel blockModel : getBlockrGame().getAllBlocksInPA()) {
    //        if (blockModel instanceof BlockModelWithBody) {
    //            UIBlockWithBody uiBlockWithBody = (UIBlockWithBody) getCorrespondingUiBlockFor(blockModel);
    //            uiBlockWithBody.setGapSize(((BlockModelWithBody) blockModel).getGapSize());
    //        }
    //    }
    //}
//
    ///**
    // * Makes sure all UIBlocks are in the correct positions, as decided in their corresponding Blocks.
    // */
    //public void adjustAllBlockPositions() {
    //    for (BlockModel blockModel : getBlockrGame().getAllBlocksInPA()) {
    //        UIBlock uiBlock = getCorrespondingUiBlockFor(blockModel);
    //        uiBlock.setPosition(blockModel.getPosition());
    //    }
    //}
//
    ///**
    // * Calls @setHighlightedBlock() on the next to be executed UIBlock.
    // */
    //public void handleHighlight(){
    //    //setHighlightedBlock(getCorrespondingUiBlockFor(blockrGame.getProgramArea().getNextProgramBlock()));
    //    setHighlightedBlock(getCorrespondingUiBlockFor(blockrGame.getToHighlightBlock()));
    //}
//
    ///**
    // * Removes the highlight of the previously highlighted UIBlock and turns on the highlight for the given UIBlock.
    // * @param block The to-be-highlighted UIBlock.
    // */
    //public void setHighlightedBlock(UIBlock block) {
    //    if (highlightedBlock != null) highlightedBlock.setHighlightStateOn(false);
    //    if (block == null) return;
//
//
    //    highlightedBlock = block;
    //    highlightedBlock.setHighlightStateOn(true);
    //}
//
    ///**
    // * Gets a collection of all the FunctionDefinitionBlocks in the Program Area.
    // * @return A Collection of UIBlocks.
    // */
    //public Collection<UIBlock> getFunctionDefinitions(){
    //    return getBlockrGame().getAllBlocksInPA().stream()
    //            .filter(c -> c instanceof FunctionDefinitionBlockModel)
    //            .map(c -> blockUIBlockMap.get(c))
    //            .collect(Collectors.toList());
    //}
}