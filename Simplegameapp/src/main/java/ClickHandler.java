public class ClickHandler {
    private GameWorld world;
    public ClickHandler(GameWorld world){
        this.world = world;
    }
    public void HandleClick(Action action){
        world.doAction(action);
    }
}
