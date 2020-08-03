package com.swop.blocks;

import java.awt.*;

public class DefaultActionData extends StdBlockData{
    private static DefaultActionData instance = null;
    public static DefaultActionData getInstance(){
        if(instance == null) instance = new DefaultActionData();
        return instance;
    }
    private DefaultActionData() {
        super(new Point(0,0), 110, 30, "");
    }
}
