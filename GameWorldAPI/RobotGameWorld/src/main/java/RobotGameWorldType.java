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
        return new RobotGameWorld();
    }

}
