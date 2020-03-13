package blocks;

import worldElements.Character;
import worldElements.GameWorld;

public abstract class ActionBlock extends Block {
    public void doAction(GameWorld world){}
    public void execute(GameWorld world){
        doAction(world);
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
