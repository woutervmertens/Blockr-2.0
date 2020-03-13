package blocks;

import worldElements.Character;
import worldElements.GameWorld;

public class IfBlock extends StatementBlock {

    @Override
    public void execute(GameWorld world) {
        if (isConditionValid(world)){
            body.getBlocks().forEach(block -> block.execute(world));
        }

    }
}
