package com.swop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.*;

class TestScenarios {
    MainViewModel mvm;
    GameController gc;

    void setUpMy() {
        gc = new GameController(new MyGameWorldType());
        mvm = new MainViewModel(new Point(0,0),300,600,new InputGameControllerFacade(gc));
        ProgramAreaViewModel pavm = new ProgramAreaViewModel(new Point(100,0),100,600,new WindowGameControllerFacade(gc));
        gc.setProgramAreaVM(pavm);
        GameWorldViewModel gwvm = new GameWorldViewModel(new Point(200,0),100,600);
        gc.setGameWorldVM(gwvm);
        PaletteViewModel pvm = new PaletteViewModel(new Point(0,0),100,600,new WindowGameControllerFacade(gc));
        gc.setPaletteVM(pvm);
        pavm.addScrollBar(new ScrollBarViewModel(new Point(190,0),600,10));
        gwvm.addScrollBar(new ScrollBarViewModel(new Point(290,0),600,10));
        pvm.addScrollBar(new ScrollBarViewModel(new Point(90,0),600,10));
    }

    void setUpRobot() {
        gc = new GameController(new RobotGameWorldType());
        mvm = new MainViewModel(new Point(0,0),400,600,new InputGameControllerFacade(gc));
        ProgramAreaViewModel pavm = new ProgramAreaViewModel(new Point(100,0),200,600,new WindowGameControllerFacade(gc));
        gc.setProgramAreaVM(pavm);
        GameWorldViewModel gwvm = new GameWorldViewModel(new Point(300,0),100,600);
        gc.setGameWorldVM(gwvm);
        PaletteViewModel pvm = new PaletteViewModel(new Point(0,0),100,600,new WindowGameControllerFacade(gc));
        gc.setPaletteVM(pvm);
        pavm.addScrollBar(new ScrollBarViewModel(new Point(290,0),600,10));
        gwvm.addScrollBar(new ScrollBarViewModel(new Point(390,0),600,10));
        pvm.addScrollBar(new ScrollBarViewModel(new Point(90,0),600,10));
    }

    //Straight to goal of MyGame
    @Test
    void scenario1(){
        setUpMy();
        //Add blocks
        mvm.handleMouseInput(MouseEvent.MOUSE_PRESSED ,40,100);     //Go Right
        mvm.handleMouseInput(MouseEvent.MOUSE_DRAGGED ,120,100);
        mvm.handleMouseInput(MouseEvent.MOUSE_RELEASED ,120,100);
        mvm.handleMouseInput(MouseEvent.MOUSE_PRESSED ,40,135);     //Go Down
        mvm.handleMouseInput(MouseEvent.MOUSE_DRAGGED ,120,130);
        mvm.handleMouseInput(MouseEvent.MOUSE_RELEASED ,120,130);
        mvm.handleMouseInput(MouseEvent.MOUSE_PRESSED ,40,100);     //Go Right
        mvm.handleMouseInput(MouseEvent.MOUSE_DRAGGED ,120,160);
        mvm.handleMouseInput(MouseEvent.MOUSE_RELEASED ,120,160);
        mvm.handleMouseInput(MouseEvent.MOUSE_PRESSED ,40,135);     //Go Down
        mvm.handleMouseInput(MouseEvent.MOUSE_DRAGGED ,120,190);
        mvm.handleMouseInput(MouseEvent.MOUSE_RELEASED ,120,190);
        //Execute
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        //Check
        assertEquals(SuccessState.GOAL_REACHED,gc.getLastSuccessState());
    }

    //Straight to goal of RobotGame
    @Test
    void scenario1_2(){
        setUpRobot();
        //Add blocks
        mvm.handleMouseInput(MouseEvent.MOUSE_PRESSED ,53,62);      //Turn left
        mvm.handleMouseInput(MouseEvent.MOUSE_DRAGGED ,239,111);
        mvm.handleMouseInput(MouseEvent.MOUSE_RELEASED ,239,111);
        mvm.handleMouseInput(MouseEvent.MOUSE_PRESSED ,22,272);     //While
        mvm.handleMouseInput(MouseEvent.MOUSE_DRAGGED ,243,145);
        mvm.handleMouseInput(MouseEvent.MOUSE_RELEASED ,243,145);
        mvm.handleMouseInput(MouseEvent.MOUSE_PRESSED ,37,136);     //WIF
        mvm.handleMouseInput(MouseEvent.MOUSE_DRAGGED ,291,149);
        mvm.handleMouseInput(MouseEvent.MOUSE_RELEASED ,291,149);
        mvm.handleMouseInput(MouseEvent.MOUSE_PRESSED ,44,60);      //Then Turn left
        mvm.handleMouseInput(MouseEvent.MOUSE_DRAGGED ,249,171);
        mvm.handleMouseInput(MouseEvent.MOUSE_RELEASED ,249,171);
        mvm.handleMouseInput(MouseEvent.MOUSE_PRESSED ,56,22);      //Go Forward
        mvm.handleMouseInput(MouseEvent.MOUSE_DRAGGED ,242,213);
        mvm.handleMouseInput(MouseEvent.MOUSE_RELEASED ,242,213);
        //Execute
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        //Check
        assertEquals(SuccessState.GOAL_REACHED,gc.getLastSuccessState());
    }

    //Undo/redo/reset focus
    @Test
    void scenario2(){

    }

    //scrollbar focus
    @Test
    void scenario3(){

    }
}
