import UIElements.Directions;
import UIElements.UICharacter;

import java.awt.*;

public class ExecuteProgramHandler {
    private UIProgramArea uiProgramArea;
    private UIGameWorld uiGameWorld;
    private UICharacter uiCharacter;

    public ExecuteProgramHandler(UIProgramArea uiProgramArea, UIGameWorld uiGameWorld) {
        this.uiProgramArea = uiProgramArea;
        this.uiGameWorld = uiGameWorld;
    }

    public void execute() {
        uiProgramArea.increaseHighlightedBlockNumber();
        //TODO: backend
    }

    public void reset() {
        uiGameWorld.Reset();
        uiProgramArea.Reset();
    }

    public void turnCharacter(Directions direction)
    {
        getCharacter();
        uiCharacter.setDirection(direction);
    }

    public void moveCharacter(int x, int y){
        getCharacter();
        uiGameWorld.switchElements(uiCharacter.getPosInGrid(),new Point(x,y));
    }

    private void getCharacter()
    {
        if (uiCharacter == null){
            uiCharacter = uiGameWorld.getCharacter();
        }
    }

    /*public void turnLeft()
    {
        getCharacter();
        switch(uiCharacter.getDirection()){
            case UP:
                turnCharacter(Directions.LEFT);
                break;
            case RIGHT:
                turnCharacter(Directions.UP);
                break;
            case DOWN:
                turnCharacter(Directions.RIGHT);
                break;
            case LEFT:
                turnCharacter(Directions.DOWN);
                break;
        }
    }

    public void turnRight()
    {
        getCharacter();
        switch(uiCharacter.getDirection()){
            case UP:
                turnCharacter(Directions.RIGHT);
                break;
            case RIGHT:
                turnCharacter(Directions.DOWN);
                break;
            case DOWN:
                turnCharacter(Directions.LEFT);
                break;
            case LEFT:
                turnCharacter(Directions.UP);
                break;
        }
    }*/
}
