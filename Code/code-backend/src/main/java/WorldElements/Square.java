package WorldElements;

public abstract class Square {
    boolean isPassable;

    public Square() {

    }

    public boolean isPassable() {
        return isPassable;
    }

    public void setPassable(boolean passable) {
        isPassable = passable;
    }
}
