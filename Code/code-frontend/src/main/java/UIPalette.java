import UIElements.*;

import java.awt.*;
import java.util.ArrayList;

public class UIPalette {
    private Point pos;
    private int blockHeight = 30;
    private int height = 600;
    private int width = 200;
    private boolean hidden;
    private ArrayList<UIBlock> uiBlocks = new ArrayList<>();

    public UIPalette(Point pos, int width, int height, int blockHeight,
                     int nbMF, int nbTL, int nbTR, int nbIf, int nbWhile, int nbNot, int nbWIF) {
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.blockHeight = blockHeight;
        hidden = false;
        createUIBlocks(nbMF, nbTL, nbTR, nbIf, nbWhile, nbNot, nbWIF);
    }

    private void createUIBlocks(int nbMF, int nbTL, int nbTR, int nbIf, int nbWhile, int nbNot, int nbWIF) {
        uiBlocks = new ArrayList<>();
        //BlockButtons
        //action
        for (int i = 0; i < nbMF; i++) {
            uiBlocks.add(new UIActionBlock(width - 20, blockHeight, new Point(0, 0),
                                            "Move Forward", BlockTypes.MoveForward));
        }
        for (int i = 0; i < nbTL; i++) {
            uiBlocks.add(new UIActionBlock(width - 20, blockHeight, new Point(0, 0),
                                            "Turn Left", BlockTypes.TurnLeft));
        }
        for (int i = 0; i < nbTR; i++) {
            uiBlocks.add(new UIActionBlock(width - 20, blockHeight, new Point(0, 0),
                                            "Turn Right", BlockTypes.TurnRight));
        }
        //statement
        for (int i = 0; i < nbIf; i++) {
            uiBlocks.add(new UIStatementBlock(width - 20, blockHeight, new Point(0, 0),
                                            "If", BlockTypes.IfStatement, 10));
        }
        for (int i = 0; i < nbWhile; i++) {
            uiBlocks.add(new UIStatementBlock(width - 20, blockHeight, new Point(0, 0),
                                            "While", BlockTypes.WhileStatement, 10));
        }
        //condition
        for (int i = 0; i < nbNot; i++) {
            uiBlocks.add(new UIConditionBlock(width - 20, blockHeight, new Point(0, 0),
                                            "NOT", BlockTypes.NotCondition));
        }
        for (int i = 0; i < nbWIF; i++) {
            uiBlocks.add(new UIConditionBlock(width - 20, blockHeight, new Point(0, 0),
                                            "Wall in front", BlockTypes.WallInFrontCondition));
        }

        //set which buttons are available
        for (UIBlock b : uiBlocks) {
            b.setAvailable(true);
        }
    }

    public void draw(Graphics g) {
        //Background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(pos.x, pos.y, width, height);

        if (hidden) return;

        int x = 10;
        int y = 10;
        int s = uiBlocks.size();
        int step = height / s;
        for (int i = 0; i < s; i++) {

            UIBlock button = uiBlocks.get(i);
            if (!button.isAvailable()) continue;
            button.setPosition(new Point(x, y + step * i));
            g.setColor(Color.black);
            g.drawRoundRect(5, step * i, width - 10, step, 5, 5);
            g.setColor(button.getColor(false));
            g.fillPolygon(button.getPolygon());
            g.setColor(Color.black);
            g.drawString(button.getText(), button.getTextPosition().x, button.getTextPosition().y);
        }
    }

    public BlockTypes getBlockTypeClicked(int y) {
        int s = uiBlocks.size();
        int i = y / (height / s);
        BlockTypes bt = BlockTypes.INVALIDTYPE;
        System.out.println(i);
        if (i < s)
            bt = uiBlocks.get(i).getType();
        return bt;
    }

    public int getWidth() {
        return width;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public int getNumBlocksAvailable() {
        return 0; /*TODO:retrieve from backend*/
    }

    public boolean isWithin(int x, int y) {
        return (x > pos.x
                && x < pos.x + width
                && y > pos.y
                && y < pos.y + height);
    }
}
