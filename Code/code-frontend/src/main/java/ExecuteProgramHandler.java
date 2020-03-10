public class ExecuteProgramHandler {
    private UIProgramArea uiProgramArea;
    private UIGameWorld uiGameWorld;

    public ExecuteProgramHandler(UIProgramArea uiProgramArea, UIGameWorld uiGameWorld) {
        this.uiProgramArea = uiProgramArea;
        this.uiGameWorld = uiGameWorld;
    }

    public void execute(){
        uiProgramArea.increaseHighlightedBlockNumber();
        //TODO: backend
    }
}
