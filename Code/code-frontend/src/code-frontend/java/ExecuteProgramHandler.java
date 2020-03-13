import UIElements.UIBlock;
import UIElements.UICharacter;

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

    public void addBlockToProgram(UIBlock block){
        blockrGame.addBlockToBlockGroudp(block.getBlock());
    }

}
