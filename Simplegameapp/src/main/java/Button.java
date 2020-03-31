import java.awt.*;

public enum Button {
    TURN_LEFT("Turn left", Color.GRAY, Action.TURN_LEFT),
    MOVE_FORWARD("Move forward", Color.LIGHT_GRAY, Action.MOVE_FORWARD),
    TURN_RIGHT("Turn right", Color.GRAY, Action.TURN_RIGHT);

    private String title;
    private Color color;
    private Point size;
    private Polygon polygon;
    private Action action;

    Button(String title, Color color, Action action){
        this.title = title;
        this.color = color;
        this.action = action;
        setSize(200,30);
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillPolygon(polygon);
        g.setColor(Color.BLACK);
        g.drawString(title,size.x/2,size.y/2);
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

}
