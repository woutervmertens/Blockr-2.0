package com.swop;

import java.util.ArrayList;

public class ViewModelFacade {
    private ArrayList<ViewModel> viewModels;
    private PaletteViewModel paletteVM;
    private ProgramAreaViewModel programAreaVM;
    private GameWorldViewModel gameWorldVM;

    public ViewModelFacade() {
        viewModels = new ArrayList<>();
    }

    private void addViewModel(ViewModel newVM){
        viewModels.add(newVM);
    }

    public PaletteViewModel getPaletteVM() {
        return paletteVM;
    }

    public void setPaletteVM(PaletteViewModel paletteVM) {
        this.paletteVM = paletteVM;
        addViewModel(paletteVM);
    }

    public ProgramAreaViewModel getProgramAreaVM() {
        return programAreaVM;
    }

    public void setProgramAreaVM(ProgramAreaViewModel programAreaVM) {
        this.programAreaVM = programAreaVM;
        addViewModel(programAreaVM);
    }

    public GameWorldViewModel getGameWorldVM() {
        return gameWorldVM;
    }

    public void setGameWorldVM(GameWorldViewModel gameWorldVM) {
        this.gameWorldVM = gameWorldVM;
        addViewModel(gameWorldVM);
    }

    /**
     * Calls all viewmodels to handle mousepress in (x,y).
     * @param x horizontal position of the mouse
     * @param y vertical position of the mouse
     */
    public void ChainMousePress(int x, int y){
        for (ViewModel vm : viewModels){
            vm.HandleMousePress(x,y);
        }
    }

    /**
     * Calls all viewmodels to handle mouserelease in (x,y).
     * @param x horizontal position of the mouse
     * @param y vertical position of the mouse
     */
    public void ChainMouseRelease(int x, int y){
        for (ViewModel vm : viewModels){
            vm.HandleMouseRelease(x,y);
        }
    }

    /**
     * Calls all viewmodels to handle mousedrag in (x,y).
     * @param x horizontal position of the mouse
     * @param y vertical position of the mouse
     */
    public void ChainMouseDrag(int x, int y){
        for (ViewModel vm : viewModels) {
            vm.HandleMouseDrag(x, y);
        }
    }
    
    /**
     * Calls all viewmodels to handle reset.
     */
    public void ChainReset(){
        for (ViewModel vm : viewModels){
            vm.HandleReset();
        }
    }
}
