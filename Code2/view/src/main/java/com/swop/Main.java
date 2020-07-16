package com.swop;

import java.lang.reflect.InvocationTargetException;

public class Main {


    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("No world argument given, shutting down.");
            return;
        }
        try {
            Class<?> clasz = Class.forName(args[0]);
            GameWorldType gameWorldType = (GameWorldType) clasz.getConstructor().newInstance();
            GameController gameController = new GameController(gameWorldType);
            java.awt.EventQueue.invokeLater(() -> {
                new MainView("My Canvas Window", gameWorldType).show();
            });
        } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
