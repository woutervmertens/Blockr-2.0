import java.awt.*;
import java.util.ArrayList;
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
        RobotGameWorld res = new RobotGameWorld();
        ArrayList<int[]> walls = new ArrayList<int[]>();
        walls.add(new int[]{3,4});
        res.setGrid(5,walls,new int[]{2,0});
        res.setRobot(Direction.RIGHT,new int[]{2,1});
        return res;
    }

}
