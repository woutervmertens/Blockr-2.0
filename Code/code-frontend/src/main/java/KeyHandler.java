public class KeyHandler {
    private UIGameWorld uiGameWorld;
    private UIProgramArea uiProgramArea;
    private ExecuteProgramHandler executeProgramHandler;

    public KeyHandler(UIGameWorld uiGameWorld, UIProgramArea uiProgramArea) {
        this.uiGameWorld = uiGameWorld;
        this.uiProgramArea = uiProgramArea;
        executeProgramHandler = new ExecuteProgramHandler(uiProgramArea,uiGameWorld);
    }

    public void stepThroughCode(){
        executeProgramHandler.execute();
    }

    public void reset(){
        uiGameWorld.Reset();
        uiProgramArea.Reset();
    }
}
