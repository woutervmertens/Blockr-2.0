import com.swop.Action;
import com.swop.GameWorld;
import com.swop.Snapshot;
import com.swop.SuccessState;

public class ClickHandler {
    private GameWorld world;
    private Snapshot snap;
    public ClickHandler(GameWorld world){
        this.world = world;
    }
    public void HandleClick(Action action){
        snap = world.createSnapshot();
        SuccessState state = world.doAction(action) ;
        if(state == SuccessState.FAILURE)
        {
            world.restoreSnapshot(snap);
        }
    }
}
