package com.swop.blocks;

import com.swop.worldElements.GameWorld;

public abstract class ActionBlock extends Block {

    private Block previous;
    private Block next;

    public void execute(GameWorld world) {
        doAction(world);
    }

    public void doAction(GameWorld world) {
        world.move();
    }

    public Block getPrevious() {
        return previous;
    }

    public void setPrevious(Block previous) {
        this.previous = previous;
    }

    public Block getNext() {
        return next;
    }

    public void setNext(Block next) {
        this.next = next;
    }
}
