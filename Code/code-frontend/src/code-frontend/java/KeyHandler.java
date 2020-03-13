public class KeyHandler {
    private UIGameWorld uiGameWorld;
    private UIProgramArea uiProgramArea;
    private ExecuteProgramHandler executeProgramHandler;

    public KeyHandler(UIGameWorld uiGameWorld, UIProgramArea uiProgramArea, BlockrGame blockrGame) {
        this.uiGameWorld = uiGameWorld;
        this.uiProgramArea = uiProgramArea;
        executeProgramHandler = new ExecuteProgramHandler(uiProgramArea, uiGameWorld, blockrGame);
    }

    public void stepThroughCode() {
        executeProgramHandler.execute();
    }

    public void reset() {
        executeProgramHandler.reset();
    }
}
