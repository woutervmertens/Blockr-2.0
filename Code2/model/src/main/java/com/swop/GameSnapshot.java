package com.swop;

/**
 * A Memento for the current state of the game.
 */
public class GameSnapshot {
    private Snapshot gameWorldSnapshot;
    private PaletteModel paletteModel;
    private ProgramAreaModel programAreaModel;

    public GameSnapshot(Snapshot gameWorldSnapshot, PaletteModel paletteModel, ProgramAreaModel programAreaModel) {
        this.gameWorldSnapshot = gameWorldSnapshot;
        this.paletteModel = paletteModel.clone();
        this.programAreaModel = programAreaModel.clone();
    }

    public Snapshot getGameWorldSnapshot() {
        return gameWorldSnapshot;
    }

    public PaletteModel getPaletteModel() {
        return paletteModel;
    }

    public ProgramAreaModel getProgramAreaModel() {
        return programAreaModel;
    }
}
