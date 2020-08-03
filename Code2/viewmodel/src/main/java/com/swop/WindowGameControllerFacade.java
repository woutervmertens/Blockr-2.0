package com.swop;

import com.swop.blocks.BlockModel;

import java.util.Collection;

public class WindowGameControllerFacade {
    GameController gameController;
    public WindowGameControllerFacade(GameController gameController){
        this.gameController = gameController;
    }

    public Collection<Action> getSupportedActions() { return gameController.getSupportedActions();}
    public Collection<Predicate> getSupportedPredicates() {return gameController.getSupportedPredicates();}
    public void setDraggedBlockVM(BlockModel bm) {gameController.setDraggedBlockVM(bm);}
}
