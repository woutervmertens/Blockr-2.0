package com.swop;

/**
 * Views call this singleton to check if they should repaint, extra repaints don't impact functionality, so multiple programs running at the same time are minimally impacted by the singleton.
 */
public class RepaintEventController {
    private static RepaintEventController instance = null;
    private RepaintEventController(){}
    public static RepaintEventController getInstance(){
        if(instance == null) instance = new RepaintEventController();
        return instance;
    }

    private boolean shouldRepaint = false;

    /**
     * Flag that a repaint is needed.
     */
    public void CallRepaint(){
        shouldRepaint = true;
    }

    /**
     * @return Boolean if a repaint is needed, if so the flag is removed.
     */
    public boolean ShouldRepaint(){
        if(shouldRepaint){
            shouldRepaint = false;
            return true;
        }
        return false;
    }
}
