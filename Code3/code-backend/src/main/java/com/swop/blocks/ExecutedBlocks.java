package com.swop.blocks;

import java.util.Stack;

public class ExecutedBlocks {
    private static ExecutedBlocks firstInstance;
    private Stack<ActionBlock> executedBlocks = new Stack<>();

    public static ExecutedBlocks getInstance() {
        if (firstInstance == null) {
            firstInstance = new ExecutedBlocks();
        }
        return firstInstance;
    }

    public ActionBlock pop() {
        return executedBlocks.pop();
    }

    public void push(ActionBlock block) {
        executedBlocks.push(block);
    }

    public void clear() {
        executedBlocks = new Stack<>();
    }
}
