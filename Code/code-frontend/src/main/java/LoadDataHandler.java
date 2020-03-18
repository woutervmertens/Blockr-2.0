import handlers.BackendConverter;

/**
 * TODO: remove the blockrGame dependency and move it to handlers package
 */
public class LoadDataHandler {
    public LoadDataHandler(BlockrGame blockrGame, UIGameWorld uiGameWorld) {
        //Get data
        BackendConverter converter = new BackendConverter();
        //load data into gameworld
        uiGameWorld.setGrid(converter.convertGrid(blockrGame.getGrid()));
        uiGameWorld.setUiCharacter(converter.convertCharacter(blockrGame.getCharacter()));
    }

}