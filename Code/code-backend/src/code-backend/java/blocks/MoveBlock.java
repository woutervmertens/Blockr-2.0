package blocks;

import worldElements.Character;
import worldElements.GameWorld;

import java.awt.*;

public class MoveBlock extends ActionBlock {
    @Override
    public void doAction(GameWorld world) {
        world.move();
    }


}
