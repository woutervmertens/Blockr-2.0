package com.swop;

import com.swop.blocks.BlockModel;
import com.swop.blocks.BlockVM;

public class InputGameControllerFacade {
    GameController gameController;
    public InputGameControllerFacade(GameController gameController){
        this.gameController = gameController;
    }

    public BlockVM getDraggedBlockVM(){
        return gameController.getDraggedBlockVM();
    }
    public void setDraggedBlockVM(BlockModel bm) { gameController.setDraggedBlockVM(bm);}
    public void dropDraggedBlock(){gameController.dropDraggedBlock();}
    public void undoCommand(){gameController.undoCommand();}
    public void redoCommand() {gameController.redoCommand();}
    public int getNrBlocksAvailable() {return gameController.getNrBlocksAvailable();}
    public void callResetCommand() {gameController.callResetCommand();}
    public void executeNext() {gameController.executeNext();}

    public SuccessState getLastSuccessState() {return gameController.getLastSuccessState();}
    public void HandleMousePress(int x, int y) {gameController.HandleMousePress(x,y);}
    public void HandleMouseDrag(int x, int y){gameController.HandleMouseDrag(x,y);}
    public void CallReleaseInVms(int x, int y){gameController.CallReleaseInVms(x,y);}
    public void resetExecution() {gameController.resetExecution();}
}
