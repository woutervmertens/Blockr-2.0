package com.swop;

import com.swop.blocks.*;

import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * A program area that handles drops of blocks in it for constructing program(s).
 */
public class ProgramArea implements PushBlocks {

    private final int radius = 10;  // Radius for connections
    /**
     * Represents the compiled code with blocks in order of how they should be executed.
     */
    private Stack<Block> program = new Stack<>();
    /**
     * List recording all blocks currently present in program area
     */
    private final List<Block> allBlocks = new ArrayList<>();

    /**
     * Executes the next block.
     */
    public void executeNext(){
        Block next = program.pop();
        /**
         * Switch:
         *  - If Statement: check conditions, if true: add children to the program
         *  - While Statement: check conditions, if true: add children and self to the program
         *  - Action: execute
         */
    }

    /**
     * Compiles the blocks into an executable program.
     *
     * Idea: return false if failed
     */
    public void compile(){
        /**
         *  - Call: add definition children to the program
         *  Add block to the program
         */
    }

    /**
     * Clears the program.
     */
    public void reset(){
        program.clear();
    }

    /**
     * Restores the Program Area and program to a given state.
     */
    public void restore(){

    }

}
