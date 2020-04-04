package com.swop;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        try {
            Class clasz = Class.forName(args[0]);
            GameWorldType gameWorldType = (GameWorldType) clasz.getConstructor().newInstance();
            java.awt.EventQueue.invokeLater(() -> {
                new MyCanvasWindow("My Canvas Window", gameWorldType).show();
            });
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
