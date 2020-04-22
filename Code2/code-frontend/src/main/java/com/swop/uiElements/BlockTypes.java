package com.swop.uiElements;

import com.swop.Action;
import com.swop.Predicate;

import java.awt.*;


public class BlockTypes {
    private final String text;
    private final int width;
    private Action action;
    private Predicate predicate;
    private final BlockType type;

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

    public BlockType getType(){return type;}

    public void setAction(Action action) {this.action = action;}
    public Action getAction() {return this.action;}

    public void setPredicate(Predicate predicate) {this.predicate = predicate;}
    public Predicate getPredicate() {return predicate;}

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
                        this.getText(), this);
            case IfStatement:
            case WhileStatement:
                return new UIStatementBlock(this.getWidth(), this.getHeight(), new Point(x, y),
                        this.getText(), this, 0);
            case NotCondition:
            case Predicate:
                return new UIConditionBlock(this.getWidth(), this.getHeight(), new Point(x, y),
                        this.getText(), this);
            default:
                throw new IllegalStateException("Trying to get an Illegal Block !");
        }
    }
}
