package uiElements;

import java.awt.*;

public enum BlockTypes {
    MoveForward("Move Forward", 110),
    TurnLeft("Turn Left", 110),
    TurnRight("Turn Right", 110),
    IfStatement("If", 110),
    WhileStatement("While", 110),
    NotCondition("Not", 35),
    WallInFrontCondition("WIF", 35);

    BlockTypes(String text, int width) {
        this.text = text;
        this.width = width;
    }

    public String getText() {
        return this.text;
    }

    private final String text;

    public int getHeight() {
        return 30;
    }
    public int getWidth() {
        return this.width;
    }
    private final int width;

    /**
     * Return new UIBlock of this type at position (x,y)
     * @param x x coordinate
     * @param y y coordinate
     * @return the new UIBlock
     */
    public UIBlock getNewUIBlock(int x, int y) {
        Point p = new Point(x, y);
        switch (this) {
            case MoveForward:
            case TurnLeft:
            case TurnRight:
                return new UIActionBlock(this.getWidth(), this.getHeight(), new Point(x, y),
                        this.getText(), this);
            case IfStatement:
            case WhileStatement:
                return new UIStatementBlock(this.getWidth(), this.getHeight(), new Point(x, y),
                        this.getText(), this, 10);
            case NotCondition:
            case WallInFrontCondition:
                return new UIConditionBlock(this.getWidth(), this.getHeight(), new Point(x, y),
                        this.getText(), this);
            default:
                throw new IllegalStateException("Trying to get an Illegal Block !");
        }
    }
}
