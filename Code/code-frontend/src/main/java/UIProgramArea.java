
import java.awt.*;

public class UIProgramArea {
    public UIProgramArea(){

    }


    public void draw(Graphics g, Point pos)
    {
        g.setColor(Color.PINK);
        g.fillRect(pos.x,pos.y,200,600);
        g.setColor(Color.red);
        g.fillPolygon(actionPolygon(new Point(210,150)));
        g.setColor(Color.orange);
        g.fillPolygon(conditionPolygon(new Point(210,20)));
    }

    private Polygon actionPolygon(Point pos)
    {
        Polygon pol = new Polygon();
        pol.addPoint(pos.x,pos.y);
        //socket
        pol.addPoint(pos.x + 10, pos.y);
        pol.addPoint(pos.x + 15,pos.y + 5);
        pol.addPoint(pos.x + 20,pos.y);

        pol.addPoint(pos.x + 180,pos.y);
        pol.addPoint(pos.x + 180, pos.y + 30);
        //plug
        pol.addPoint(pos.x + 20, pos.y + 30);
        pol.addPoint(pos.x + 15, pos.y + 35);
        pol.addPoint(pos.x + 10, pos.y + 30);

        pol.addPoint(pos.x,pos.y + 30);
        return pol;
    }

    private Polygon conditionPolygon(Point pos)
    {
        Polygon pol = new Polygon();
        pol.addPoint(pos.x,pos.y);
        pol.addPoint(pos.x + 180,pos.y);
        //plug
        pol.addPoint(pos.x + 180, pos.y + 10);
        pol.addPoint(pos.x + 185,pos.y + 15);
        pol.addPoint(pos.x + 180,pos.y + 20);

        pol.addPoint(pos.x + 180, pos.y + 30);
        pol.addPoint(pos.x,pos.y + 30);
        //socket
        pol.addPoint(pos.x, pos.y + 10);
        pol.addPoint(pos.x + 5,pos.y + 15);
        pol.addPoint(pos.x,pos.y + 20);
        return pol;
    }
}
