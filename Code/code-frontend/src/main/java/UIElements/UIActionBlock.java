package UIElements;

import blocks.MoveBlock;
import blocks.TurnBlock;

import java.awt.*;
import blocks.*;

public class UIActionBlock extends UIBlock {
    private final Block block;

    public UIActionBlock(int width, int height, Point position, String text, BlockTypes type) {
        super(width, height, position, text, type);
        switch (type) {
            case MoveForward:
                this.block = new MoveBlock();
                break;
            case TurnLeft:
            case TurnRight:
                this.block = new TurnBlock();
                break;
            default:
                throw new IllegalArgumentException("Not an Action Block !");
        }
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
