package blocks;

import worldElements.Character;

import java.awt.*;

public class MoveBlock extends ActionBlock {
    @Override
    public void doAction(Character character) {
        int[] pos = character.getPosition();
        switch (character.getDirection()) {
            case UP:
                pos[1]--;
                break;
            case RIGHT:
                pos[0]++;
                break;
            case DOWN:
                pos[1]++;
                break;
            case LEFT:
                pos[0]--;
                break;
        }
    }


}
