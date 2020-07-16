package com.swop;

import com.swop.blocks.Block;
import com.swop.blocks.StdBlockData;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class GameController {
    private ArrayList<ViewModel> viewModels;
    private GameWorldType gameWorldType;

    private StdBlockData defaultActionData;
    private StdBlockData defaultPredicateData;
    private StdBlockData defaultBodyBlockData;

    public GameController(GameWorldType gameWorldType){
        this.gameWorldType = gameWorldType;
        this.viewModels = new ArrayList<>();
        defaultActionData = new StdBlockData(
                new Point(0,0),
                110,
                30,
                ""
        );
        defaultPredicateData = new StdBlockData(
                new Point(0,0),
                40,
                30,
                ""
        );
        defaultBodyBlockData = new StdBlockData(
                new Point(0,0),
                70,
                30,
                30,
                10,
                0,
                ""
        );
    }

    public void AddViewModel(ViewModel newVM){
        viewModels.add(newVM);
    }

    public void RemoveViewModel(ViewModel oldVM){
        viewModels.remove(oldVM);
    }

    public void HandleMousePress(int x, int y){
        for (ViewModel vm : viewModels){
            vm.HandleMousePress(x,y);
        }
    }

    public void HandleMouseRelease(Block draggedBlock, int x, int y){
        for (ViewModel vm : viewModels){
            vm.HandleMouseRelease(draggedBlock,x,y);
        }
    }

    public void HandleMouseDrag(int x, int y){
        for (ViewModel vm : viewModels){
            vm.HandleMouseDrag(x,y);
        }
    }

    public int getNrBlocksAvailable() {
        return 20;
    }

    public void undo() {
    }

    public void redo() {
    }

    public void executeNext() {
    }

    public void resetExecution() {
    }

    public Collection<Action> getSupportedActions()
    {
        return gameWorldType.getSupportedActions();
    }

    public Collection<Predicate> getSupportedPredicates(){
        return gameWorldType.getSupportedPredicates();
    }


    public StdBlockData getDefaultActionData() {
        return defaultActionData;
    }

    public StdBlockData getDefaultPredicateData() {
        return defaultPredicateData;
    }

    public StdBlockData getDefaultBodyBlockData() {
        return defaultBodyBlockData;
    }
}
