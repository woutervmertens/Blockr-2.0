package com.swop;

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
        mvm = new MainViewModel(new Point(0,0),600,600,new InputGameControllerFacade(gc));
        ProgramAreaViewModel pavm = new ProgramAreaViewModel(new Point(150,0),300,600,new WindowGameControllerFacade(gc));
        gc.setProgramAreaVM(pavm);
        GameWorldViewModel gwvm = new GameWorldViewModel(new Point(450,0),150,600);
        gc.setGameWorldVM(gwvm);
        PaletteViewModel pvm = new PaletteViewModel(new Point(0,0),150,600,new WindowGameControllerFacade(gc));
        gc.setPaletteVM(pvm);
        pavm.addScrollBar(new ScrollBarViewModel(new Point(440,0),600,10));
        gwvm.addScrollBar(new ScrollBarViewModel(new Point(590,0),600,10));
        pvm.addScrollBar(new ScrollBarViewModel(new Point(140,0),600,10));
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
        setUpMy();
        //Place Blocks
        mvm.handleMouseInput(MouseEvent.MOUSE_PRESSED ,40,100);     //Go Right
        mvm.handleMouseInput(MouseEvent.MOUSE_DRAGGED ,120,100);
        mvm.handleMouseInput(MouseEvent.MOUSE_RELEASED ,120,100);
        mvm.handleMouseInput(MouseEvent.MOUSE_PRESSED ,40,135);     //Go Down
        mvm.handleMouseInput(MouseEvent.MOUSE_DRAGGED ,120,130);
        mvm.handleMouseInput(MouseEvent.MOUSE_RELEASED ,120,130);
        mvm.handleMouseInput(MouseEvent.MOUSE_PRESSED ,40,100);     //Go Right
        mvm.handleMouseInput(MouseEvent.MOUSE_DRAGGED ,120,160);
        mvm.handleMouseInput(MouseEvent.MOUSE_RELEASED ,120,160);
        //Undo
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,90,true,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,90,true,false);
        //Redo
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,90,true,true);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,90,true,true);
        //Place last block
        mvm.handleMouseInput(MouseEvent.MOUSE_PRESSED ,40,135);     //Go Down
        mvm.handleMouseInput(MouseEvent.MOUSE_DRAGGED ,120,190);
        mvm.handleMouseInput(MouseEvent.MOUSE_RELEASED ,120,190);
        //Execute
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        //Reset
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,27,false,false);
        //Execute
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        //Check
        assertEquals(SuccessState.GOAL_REACHED,gc.getLastSuccessState());
    }

    //scrollbar and function focus
    @Test
    void scenario3(){
        setUpRobot();
        //Place blocks
        moveFromTo(42,99,221,39);        //Turn right
        moveFromTo(31,324,222,127);      //0
        moveFromTo(39,374,222,70);       //->0
        moveFromTo(73,19,229,151);       //move forward
        moveFromTo(41,328,222,233);      //1
        moveFromTo(35,406,234,184);      //->1
        moveFromTo(46,102,235,257);       //turn right
        moveFromTo(37,322,221,356);      //2
        moveFromTo(44,449,233,293);      //->2

        moveFromTo(49,21,232,379);       //move forward
        moveFromTo(29,321,218,482);      //3
        moveFromTo(37,496,231,418);      //->3
        moveFromTo(37,102,232,509);       //turn right
        //Scroll down program area
        moveFromTo(446,58,428,259);
        //Place blocks
        moveFromTo(31,318,216,219);      //4
        moveFromTo(40,23,228,246);       //move forward
        //Scroll down palette
        moveFromTo(145,68,147,163);
        //Place last block
        moveFromTo(33,288,228,156);      //->4
        //Execute
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        mvm.handleKeyInput(KeyEvent.KEY_PRESSED,116,false,false);
        //Check
        assertEquals(SuccessState.GOAL_REACHED,gc.getLastSuccessState());
    }

    private void moveFromTo(int fromX,int fromY, int toX, int toY){
        mvm.handleMouseInput(MouseEvent.MOUSE_PRESSED ,fromX,fromY);
        mvm.handleMouseInput(MouseEvent.MOUSE_DRAGGED ,toX,toY);
        mvm.handleMouseInput(MouseEvent.MOUSE_RELEASED ,toX,toY);
    }
}
