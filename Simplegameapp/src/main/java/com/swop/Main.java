package com.swop;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args){
        try {
            Class clasz = Class.forName(args[0]);
            GameWorldType gameWorldType = (GameWorldType) clasz.getConstructor().newInstance();
            java.awt.EventQueue.invokeLater(() -> {
                new MyCanvasWindow("Simple game app",gameWorldType).show();
            });
        } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
