import java.awt.*;
import java.util.Arrays;
import java.util.Collection;

public class RobotGameWorldType implements GameWorldType{

    @Override
    public Collection getSupportedActions() {
        return Arrays.asList(Action.MOVE_FORWARD,Action.TURN_LEFT,Action.TURN_RIGHT);
    }

    @Override
    public Collection getSupportedPredicates() {
        return Arrays.asList(Predicate.WALL_IN_FRONT);
    }

    @Override
    public GameWorld createNewInstance() {
        Square[][] grid = new Square[][]{};
        Point drawPosition = new Point(0,0);
        return new RobotGameWorld(grid,30,drawPosition,new Robot());
    }

}
