package com.swop.uiElements;

import com.swop.Action;
import com.swop.Predicate;

import java.awt.*;


public class BlockTypes {
    private final String text;
    private final int width;
    private final BlockType type;
    private Action action;
    private Predicate predicate;

    public BlockTypes(String text, int width, BlockType type) {
        this.text = text;
        this.width = width;
        this.type = type;
    }

    public String getText() {
        return this.text;
    }

    public int getHeight() {
        return 30;
    }

    public int getWidth() {
        return this.width;
    }

    public BlockType getType() {
        return type;
    }

    public Action getAction() {
        return this.action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Return new UIBlock of this type at position (x,y)
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return the new UIBlock
     */
    public UIBlock getNewUIBlock(int x, int y) {
        switch (this.type) {
            case ActionType:
                return new UIActionBlock(this.getWidth(), this.getHeight(), new Point(x, y),
                        this.getText(), this, Color.red, new Color(255,140,140));
            case IfStatement:
            case WhileStatement:
                return new UIStatementBlock(this.getWidth(), this.getHeight(), new Point(x, y),
                        this.getText(), this,Color.cyan, new Color(200,255,255) , 0);
            case NotCondition:
            case Predicate:
                return new UIConditionBlock(this.getWidth(), this.getHeight(), new Point(x, y),
                        this.getText(), this, Color.orange, new Color(255,255,145));
            case FunctionCall:
                return new UIFunctionCall(this.getWidth(),this.getHeight(),new Point(x,y),this.getText(), this, Color.gray, Color.white);
            case FunctionDefinition:
                return new UIFunctionDefinition(this.getWidth(),this.getHeight(),new Point(x,y),this.getText(), this, Color.white, Color.white, 0);
            default:
                throw new IllegalStateException("Trying to get an Illegal Block !");
        }
    }
}
