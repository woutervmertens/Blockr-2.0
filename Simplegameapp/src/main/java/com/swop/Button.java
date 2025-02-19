package com.swop;

import java.awt.*;

public class Button {
    private String title;
    private Color color;
    private Point size = new Point();
    private Polygon polygon;
    private Action action;

    public Button(String title, Color color, Action action){
        this.title = title;
        this.color = color;
        this.action = action;
        setSize(200,30);
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillPolygon(polygon);
        g.setColor(Color.BLACK);
        g.drawString(title,polygon.getBounds().x + size.x/3,polygon.getBounds().y + size.y/2 + 5);
    }

    public void setSize(int x, int y){
        size.setLocation(x,y);
        polygon = new Polygon();
        polygon.addPoint(0,0);
        polygon.addPoint(size.x,0);
        polygon.addPoint(size.x,size.y);
        polygon.addPoint(0,size.y);
    }

    public void setLocation(int x, int y){
        polygon.translate(x,y);
    }

    public boolean isClicked(int x, int y){
        return (x > polygon.getBounds().x &&
            x < polygon.getBounds().x + size.x &&
            y > polygon.getBounds().y &&
            y < polygon.getBounds().y + size.y);
    }

    public Action getAction(){
        return action;
    }

    //testing
    protected Point getSize(){return size;}
    protected Polygon getPolygon() {return polygon;}
}
