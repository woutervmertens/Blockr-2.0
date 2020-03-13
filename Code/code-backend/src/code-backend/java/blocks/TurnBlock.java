package blocks;

import worldElements.Character;
import worldElements.Direction;
import worldElements.GameWorld;

public class TurnBlock extends ActionBlock {
    public TurnBlock(Direction direction){
        this.direction = direction;
    }
    private Direction direction;

    @Override
    public void doAction(GameWorld world) {
        world.turn();
    }



}
