import java.util.Collection;

public class RobotGameWorldType implements GameWorldType{

    @Override
    public Collection getSupportedActions() {
        return null;
    }

    @Override
    public Collection getSupportedPredicates() {
        return null;
    }

    @Override
    public GameWorld createNewInstance() {
        return new RobotGameWorld();
    }
}
