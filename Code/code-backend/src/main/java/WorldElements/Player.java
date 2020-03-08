package WorldElements;

import java.awt.*;

public class Player{
    Point position;
    Directions direction;

    public Player(Point position, Directions direction) {
        this.position = position;
        this.direction = direction;
    }

    public Player(Player p)
    {
        this.position = p.getPosition();
        this.direction = p.getDirection();
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }
}
