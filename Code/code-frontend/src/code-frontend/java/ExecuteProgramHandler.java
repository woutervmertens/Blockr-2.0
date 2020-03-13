import UIElements.Directions;
import UIElements.UICharacter;

import java.awt.*;

public class ExecuteProgramHandler {
    private UIProgramArea uiProgramArea;
    private UIGameWorld uiGameWorld;
    private UICharacter uiCharacter;
    private BlockrGame blockrGame;
    private BackendConverter converter;

    public ExecuteProgramHandler(UIProgramArea uiProgramArea, UIGameWorld uiGameWorld, BlockrGame blockrGame) {
        this.uiProgramArea = uiProgramArea;
        this.uiGameWorld = uiGameWorld;
        this.blockrGame = blockrGame;
        converter = new BackendConverter();
    }

    public void execute() {
        uiProgramArea.increaseHighlightedBlockNumber();
        blockrGame.executeBlock();
    }

    public void reset() {
        blockrGame.reset();
        uiGameWorld.setGrid(converter.convertGrid(blockrGame.getGrid()));
    }

    public void turnCharacter(Directions direction)
    {
        getCharacter();
        uiCharacter.setDirection(direction);
    }

    public void moveCharacter(int x, int y){
        getCharacter();
        //uiGameWorld.switchElements(uiCharacter.getPosInGrid(),new Point(x,y));
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
