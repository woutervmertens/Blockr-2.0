package UIElements;

import java.awt.*;

public class UIStatementBlock extends UIBlock{
    int gapSize;
    public UIStatementBlock(int width, int height, Point position, String text, BlockTypes type, int gapSize) {
        super(width, height, position, text, type);
        this.gapSize = gapSize;
        color = Color.CYAN;
        highlightColor = Color.getHSBColor(180,100,30);
    }

    public int getGapSize() {
        return gapSize;
    }

    public void setGapSize(int gapSize) {
        this.gapSize = gapSize;
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public Polygon getPolygon(){
        Polygon pol = new Polygon();
        int step = height/6;
        pol.addPoint(position.x,position.y);
        //socket top
        pol.addPoint(position.x + step*2, position.y);
        pol.addPoint(position.x + step*3,position.y + step);
        pol.addPoint(position.x + step*4,position.y);

        pol.addPoint(position.x + width,position.y);
        //plug right
        pol.addPoint(position.x + width,        position.y + step*2);
        pol.addPoint(position.x + width + step, position.y + step*3);
        pol.addPoint(position.x + width,        position.y + step*4);

        pol.addPoint(position.x + width, position.y + height);

        //gap
        pol.addPoint(position.x + height, position.y + height);
        pol.addPoint(position.x + height, position.y + height + gapSize);
        pol.addPoint(position.x + width,position.y + gapSize + height);
        pol.addPoint(position.x + width, position.y + height*2 + gapSize);

        //plug bottom
        pol.addPoint(position.x + step*4, position.y + height*2 + gapSize);
        pol.addPoint(position.x + step*3, position.y + height*2 + gapSize + step);
        pol.addPoint(position.x + step*2, position.y + height*2 + gapSize);

        pol.addPoint(position.x,position.y + height*2 + gapSize);
        return pol;
    }
}
