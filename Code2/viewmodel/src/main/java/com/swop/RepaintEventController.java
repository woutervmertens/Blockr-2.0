package com.swop;


public class RepaintEventController {
    private static RepaintEventController instance = null;
    private RepaintEventController(){}
    public static RepaintEventController getInstance(){
        if(instance == null) instance = new RepaintEventController();
        return instance;
    }

    private boolean shouldRepaint = false;

    public void CallRepaint(){
        shouldRepaint = true;
    }
    public boolean ShouldRepaint(){
        if(shouldRepaint){
            shouldRepaint = false;
            return true;
        }
        return false;
    }
}
