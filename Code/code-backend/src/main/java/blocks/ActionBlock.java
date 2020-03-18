package blocks;

import worldElements.GameWorld;

public abstract class ActionBlock extends Block {

    public void execute(GameWorld world){
        doAction(world);
    }

    public void doAction(GameWorld world){
        world.move();
    }


    public Block getPrevious() {
        return previous;
    }

    public void setPrevious(Block previous) {
        this.previous = previous;
    }

    private Block previous;

    public Block getNext() {
        return next;
    }

    public void setNext(Block next) {
        this.next = next;
    }

    private Block next;
}
