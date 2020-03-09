package Blocks;

import WorldElements.Character;

import java.awt.*;

public class MoveBlock extends ActionBlock {
    @Override
    public void doAction(Character character) {
        Point pos = character.getPosition();
        switch (character.getDirection()) {
            case UP:
                pos.y--;
                break;
            case RIGHT:
                pos.x++;
                break;
            case DOWN:
                pos.y++;
                break;
            case LEFT:
                pos.x--;
                break;
        }
    }
}
