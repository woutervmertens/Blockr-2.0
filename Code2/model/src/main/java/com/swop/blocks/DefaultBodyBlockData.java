package com.swop.blocks;

import java.awt.*;

public class DefaultBodyBlockData extends StdBlockData{
    private static DefaultBodyBlockData instance = null;
    public static DefaultBodyBlockData getInstance(){
        if(instance == null) instance = new DefaultBodyBlockData();
        return instance;
    }

    private DefaultBodyBlockData() {
        super(new Point(0,0), 70, 30, 30, 10, 0, "");
    }
}
