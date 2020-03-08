package Blocks;

import WorldElements.Player;

import java.awt.*;

public class MoveBlock extends ActionBlock {
    @Override
    public void doAction(Player player) {
        Point pos = player.getPosition();
        switch (player.getDirection()) {
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
