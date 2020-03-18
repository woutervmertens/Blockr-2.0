package com.swop;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new MyCanvasWindow("My Canvas Window").show();
        });
    }
}
