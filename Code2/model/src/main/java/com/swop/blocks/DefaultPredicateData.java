package com.swop.blocks;

import java.awt.*;

public class DefaultPredicateData extends StdBlockData{
    private static DefaultPredicateData instance = null;
    public static DefaultPredicateData getInstance(){
        if(instance == null) instance = new DefaultPredicateData();
        return instance;
    }
    private DefaultPredicateData() {
        super(new Point(0,0), 40, 30, "");
    }
}
