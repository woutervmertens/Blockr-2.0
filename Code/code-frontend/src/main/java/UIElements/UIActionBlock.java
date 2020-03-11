package UIElements;

import java.awt.*;

public class UIActionBlock extends UIBlock {
    public UIActionBlock(int width, int height, Point position, String text, BlockTypes type) {
        super(width, height, position, text, type);
        color = Color.red;
        highlightColor = Color.getHSBColor(0, 80, 100); //light red
        //plug
        connectionPoints.add(new Point(position.x, position.y + height));
    }


    @Override
    public Polygon getPolygon() {
        Polygon pol = new Polygon();
        int step = height / 6;
        pol.addPoint(position.x, position.y);
        //socket
        pol.addPoint(position.x + step * 2, position.y);
        pol.addPoint(position.x + step * 3, position.y + step);
        pol.addPoint(position.x + step * 4, position.y);

        pol.addPoint(position.x + width, position.y);
        pol.addPoint(position.x + width, position.y + height);
        //plug
        pol.addPoint(position.x + step * 4, position.y + height);
        pol.addPoint(position.x + step * 3, position.y + height + step);
        pol.addPoint(position.x + step * 2, position.y + height);

        pol.addPoint(position.x, position.y + height);
        return pol;
    }
}
