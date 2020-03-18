import handlers.BackendConverter;
import uiElements.UIBlock;
import uiElements.UICharacter;
import windowElements.UIProgramArea;

/**
 * TODO: remove the blockrGame dependency and move it to handlers package
 */
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
        uiProgramArea.highlightNextBlock();
        blockrGame.executeBlock();
        // TODO: fix bug of re-executing directly
        // TODO: fix bug of not moving forward twice
    }

    public void reset() {
        blockrGame.reset();
        uiGameWorld.setGrid(converter.convertGrid(blockrGame.getGrid()));
        uiProgramArea.restartProgram();
    }

    public void addBlockToProgram(UIBlock block){
        blockrGame.addBlockToBlockGroup(block.getBlock());
    }

    public void createNewBlockGroup(UIBlock block)
    {
        blockrGame.createNewBlockGroup(block.getBlock());
    }

}
