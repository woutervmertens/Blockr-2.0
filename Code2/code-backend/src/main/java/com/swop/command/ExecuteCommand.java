package com.swop.command;

import com.swop.BlockrGame;
import com.swop.Snapshot;
import com.swop.blocks.Block;
import com.swop.blocks.BlockWithBody;
import com.swop.blocks.Executable;
import com.swop.blocks.ExecuteType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExecuteCommand extends BlockrGameCommand {
    private Snapshot snapshot;
    private final Block block;
    private Block nextProgramBlock;
    private List<Block> allBlocks;
    private List<Block> program;

    public ExecuteCommand(BlockrGame blockrGame, Block block) {
        super(blockrGame);
        this.block = block;
    }

    @Override
    public void execute() {
        snapshot = blockrGame.getGameWorld().createSnapshot();
        nextProgramBlock = blockrGame.getProgramArea().getNextProgramBlock();
        allBlocks = new ArrayList<>(blockrGame.getAllBlocksInPA());
        program = new LinkedList<>(blockrGame.getProgram());

        if (block.getExecuteType() != ExecuteType.NonExecutable) {
            ((Executable) block).execute();
        }
        if (nextProgramBlock != null && !nextProgramBlock.isBusy()) {
            blockrGame.getProgramArea().setNextProgramBlock();
        }
    }

    @Override
    public void undo() {
        blockrGame.getGameWorld().restoreSnapshot(snapshot);
        blockrGame.getProgramArea().restore(allBlocks, program, nextProgramBlock);


//        // Restore the effective nextBlock (to be executed, can be a body of a body of a body ...)
//        Block parentBlock = nextProgramBlock;
//        // 1) Get most deep next body block
//        while (nextProgramBlock instanceof BlockWithBody) {
//            parentBlock = nextProgramBlock;
//            nextProgramBlock = ((BlockWithBody) nextProgramBlock).getNextBodyBlock();
//        }
//        // 2) Select the previously executed body block as the next body block.
//        while (parentBlock instanceof BlockWithBody) {
//            int i = ((BlockWithBody) parentBlock).getBodyBlocks().indexOf(nextProgramBlock);
//            // Go to parent bcs this is the highest body block
//            if (i <= 0) {
//                nextProgramBlock = parentBlock;
//                parentBlock = parentBlock.getParentBlock();
//                if (parentBlock == null) break;
//            }
//            // Next body becomes the previous body block
//            else {
//                ((BlockWithBody) parentBlock).setNextBodyBlock(((BlockWithBody) parentBlock).getBodyBlocks().get(i - 1));
//                break;
//            }
//        }

        // TODO: highlight in not updated for body (instead the whole statement gets highlighted)
    }

//    private void restorePreviousBlockState(Block previousBlock, Block currentNextBlock) {
//        // Restore block's nextBodyBlock of the eventual previous block with body
//        if (previousBlock != currentNextBlock) {
//            if (previousBlock instanceof BlockWithBody && ((BlockWithBody) previousBlock).getNextBodyBlock() == null) {
//                if (!(((BlockWithBody) nextProgramBlock).getBodyBlocks().isEmpty())) {
//                    // Set the last body as next
//                    ((BlockWithBody) nextProgramBlock).setNextBodyBlock(((BlockWithBody) nextProgramBlock).getBodyBlocks().get(((BlockWithBody) block).getBodyBlocks().size() - 1));
//                }
//            }
//        }
//    }
}
