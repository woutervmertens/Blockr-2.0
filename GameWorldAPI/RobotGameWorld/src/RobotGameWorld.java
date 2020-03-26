import java.awt.*;

public class RobotGameWorld implements GameWorld {
    @Override
    public SuccessState doAction(Action action) {
        return null;
    }

    @Override
    public boolean evaluate(Predicate predicate) {
        return false;
    }

    @Override
    public Snapshot createSnapshot() {
        return null;
    }

    @Override
    public void restoreSnapshot(Snapshot snapshot) {

    }

    @Override
    public void paint(Graphics g) {

    }
}
