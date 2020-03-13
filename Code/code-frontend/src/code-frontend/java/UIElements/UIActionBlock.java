package UIElements;

import blocks.Block;
import blocks.MoveBlock;
import blocks.TurnBlock;
import worldElements.Direction;

import java.awt.*;
import java.util.ArrayList;

public class UIActionBlock extends UIBlock implements VerticallyConnectable {
    private final Block block;

    public UIActionBlock(int width, int height, Point position, String text, BlockTypes type) {
        super(width, height, position, text, type);
        switch (type) {
            case MoveForward:
                this.block = new MoveBlock();
                break;
            case TurnLeft:
                this.block = new TurnBlock(Direction.LEFT);
                break;
            case TurnRight:
                this.block = new TurnBlock(Direction.RIGHT);
                break;
            default:
                throw new IllegalArgumentException("Not an Action blocks.Block !");
        }
        color = Color.red;
        highlightColor = Color.getHSBColor(0, 80, 100); //light red
    }

    public Block getBlock() {
        return this.block;
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


    @Override
    public Point getPlugPosition() {
        return new Point(position.x + step * 3, position.y + height + step);
    }

    @Override
    public Point getSocketPosition() {
        return new Point(position.x + step * 3, position.y + step);
    }
}
