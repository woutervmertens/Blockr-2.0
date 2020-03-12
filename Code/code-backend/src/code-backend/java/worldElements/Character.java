package worldElements;

public class Character {

    public Character() {
        new Character(new int[] {0,0});
    }

    public Character(int[] position) {
        this.position = position;
        this.direction = Direction.RIGHT;
    }

    /**
     * Current position of this character on the grid of its world.
     */
    private int[] position = {0, 0};

    public int[] getPosition() {
        return position;
    }

    /**
     * Move this character one cell in the given direction on the grid of its world.
     */
    public void move(Direction direction) {
        // TODO: When not possible to move to the given direction throw exception.
        // TODO: Or alternatively let this class not be aware of the world and world
        // TODO: will only call this method when it's legal.

        switch (direction) {
            case UP:
                setPosition(new int[]{getPosition()[0], getPosition()[1] + 1});
                break;

            case DOWN:
                setPosition(new int[]{getPosition()[0], getPosition()[1] - 1});
                break;

            case LEFT:
                setPosition(new int[]{getPosition()[0] - 1, getPosition()[1]});
                break;

            case RIGHT:
                setPosition(new int[]{getPosition()[0] + 1, getPosition()[1]});
                break;
        }
    }

    private void setPosition(int[] position) {
        this.position = position;
    }

    /**
     * Current direction of this character in its world.
     */
    private Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
