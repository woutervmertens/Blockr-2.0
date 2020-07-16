package com.swop;

import java.util.ArrayList;

public class GameController {
    private ArrayList<ViewModel> viewModels;
    private GameWorldType gameWorldType;

    public GameController(GameWorldType gameWorldType){
        this.gameWorldType = gameWorldType;
        this.viewModels = new ArrayList<>();
    }

    public void AddViewModel(ViewModel newVM){
        viewModels.add(newVM);
    }

    public void RemoveViewModel(ViewModel oldVM){
        viewModels.remove(oldVM);
    }

    public void HandleClick(int x, int y){
        for (ViewModel vm : viewModels){
            vm.HandleClick(x,y);
        }
    }
}
