import worldElements.Character;
import worldElements.GameWorld;
import worldElements.Square;

public class BlockrGame {
    private GameWorld gameWorld;
    private Program program;



    public Character executeBlock() {
        program.execute();
        return gameWorld.getCharacter();
    }

    public Character getCharacter(){
        return gameWorld.getCharacter();
    }

    public Square[][] getGrid()
    {
        return gameWorld.getGrid();
    }

    public void reset()
    {
        gameWorld.reset();
        program.reset();
    }


}
