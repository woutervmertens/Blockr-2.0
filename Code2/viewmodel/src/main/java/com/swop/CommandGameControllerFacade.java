package com.swop;

import com.swop.blocks.BlockModel;

public class CommandGameControllerFacade {
    GameController gameController;
    public CommandGameControllerFacade(GameController gameController){
        this.gameController = gameController;
    }

    public GameSnapshot createSnapshot(){return gameController.createSnapshot();}
    public void restoreSnapshot(GameSnapshot snapshot){gameController.restoreSnapshot(snapshot);}
    public void addBlock(BlockModel blockModel){gameController.addBlock(blockModel);}
    public void deleteBlock(BlockModel blockModel) {gameController.deleteBlock(blockModel);}
    public SuccessState getLastSuccessState() {return gameController.getLastSuccessState();}
    public void setLastSuccessState(SuccessState lastSuccessState) {gameController.setLastSuccessState(lastSuccessState);}
    public void resetExecution() {gameController.resetExecution();}

}
