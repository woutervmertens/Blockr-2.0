package UIElements;

import java.awt.*;

public class UIConditionBlock extends UIBlock
{
    public UIConditionBlock(int width, int height, Point position, String text, BlockTypes type) {
        super(width, height, position, text, type);
        color = Color.orange;
        highlightColor = Color.getHSBColor(45,65,100); //light orange
        //plug
        connectionPoints.add(new Point(position.x + width,position.y));
    }

    @Override
    public Polygon getPolygon(){
        Polygon pol = new Polygon();
        int step = height/6;
        pol.addPoint(position.x,position.y);
        pol.addPoint(position.x + width,position.y);
        //plug
        pol.addPoint(position.x + width,        position.y + step*2);
        pol.addPoint(position.x + width + step, position.y + step*3);
        pol.addPoint(position.x + width,        position.y + step*4);

        pol.addPoint(position.x + width, position.y + height);
        pol.addPoint(position.x,            position.y + height);
        //socket
        pol.addPoint(position.x,            position.y + step*2);
        pol.addPoint(position.x + step,  position.y + step*3);
        pol.addPoint(position.x,            position.y + step*4);
        return pol;
    }
}
