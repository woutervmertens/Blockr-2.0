package com.swop.uiElements;

import com.swop.Action;
import com.swop.Predicate;

import java.awt.*;

/**
 * Class holding information shared by all block types, and creator of UIBlocks
 */
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
        stdBlockData data = new stdBlockData(this.getWidth(), this.getHeight(), new Point(x,y), this.getText(), this);
        switch (this.type) {
            case ActionType:
                data.setColor(Color.red);
                data.setHighlightColor(new Color(255,140,140));
                return new UIActionBlock(data);
            case IfStatement:
            case WhileStatement:
                data.setColor(Color.cyan);
                data.setHighlightColor(new Color(200,255,255));
                return new UIStatementBlock(data, 0);
            case NotCondition:
            case Predicate:
                data.setColor(Color.orange);
                data.setHighlightColor(new Color(255,255,145));
                return new UIConditionBlock(data);
            case FunctionCall:
                data.setColor(Color.gray);
                data.setHighlightColor(Color.white);
                return new UIFunctionCall(data);
            case FunctionDefinition:
                data.setColor(Color.white);
                data.setHighlightColor(Color.white);
                return new UIFunctionDefinition(data,0);
            default:
                throw new IllegalStateException("Trying to get an Illegal Block !");
        }
    }

    protected class stdBlockData{
        private int width, height;
        private Point position;
        private String text;
        private BlockTypes blockTypes;
        private Color color, highlightColor;

        protected stdBlockData(int width, int height, Point position, String text, BlockTypes blockTypes){
            this.width = width;
            this.height = height;
            this.position = position;
            this.text = text;
            this.blockTypes = blockTypes;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public Point getPosition() {
            return position;
        }

        public String getText() {
            return text;
        }

        public BlockTypes getBlockTypes() {
            return blockTypes;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Color getHighlightColor() {
            return highlightColor;
        }

        public void setHighlightColor(Color highlightColor) {
            this.highlightColor = highlightColor;
        }
    }
}
