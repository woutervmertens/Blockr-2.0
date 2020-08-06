package com.swop;

/**
 * The data for the GameWorld Window
 */
public class GameWorldModel {
    GameWorld gameWorld;
    GameWorldType gameWorldType;
    public void setGameWorld(GameWorld gw, GameWorldType gwType){
        this.gameWorld = gw;
        this.gameWorldType = gwType;
    }

}
