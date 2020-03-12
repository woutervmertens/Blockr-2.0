package blocks;

import worldElements.Character;
import worldElements.Direction;

public class TurnBlock extends ActionBlock {
    public TurnBlock(Direction direction){
        this.direction = direction;
    }
    private Direction direction;

    @Override
    public void doAction(Character character) {
        if (direction == Direction.LEFT) {
            switch (character.getDirection()) {
                case UP:
                    character.setDirection(Direction.LEFT);
                    break;
                case RIGHT:
                    character.setDirection(Direction.UP);
                    break;
                case DOWN:
                    character.setDirection(Direction.RIGHT);
                    break;
                case LEFT:
                    character.setDirection(Direction.DOWN);
                    break;
            }
        } else {
            switch (character.getDirection()) {
                case UP:
                    character.setDirection(Direction.RIGHT);
                    break;
                case RIGHT:
                    character.setDirection(Direction.DOWN);
                    break;
                case DOWN:
                    character.setDirection(Direction.LEFT);
                    break;
                case LEFT:
                    character.setDirection(Direction.UP);
                    break;
            }

        }
    }


}
