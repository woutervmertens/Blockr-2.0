import UIElements.*;

import java.awt.*;

public class ClickOnBlockHandler {
    //create a block at pos(x,y) of BlockType type
    public UIBlock createBlock(BlockTypes type, int x, int y, int width, int height) {
        Point p = new Point(x, y);
        switch (type) {
            case MoveForward:
                return new UIActionBlock(width, height, p, "Move forward", type);
            case TurnLeft:
                return new UIActionBlock(width, height, p, "Turn left", type);
            case TurnRight:
                return new UIActionBlock(width, height, p, "Turn right", type);
            case IfStatement:
                return new UIStatementBlock(width, height, p, "If", type, 10);
            case WhileStatement:
                return new UIStatementBlock(width, height, p, "While", type, 10);
            case NotCondition:
                return new UIConditionBlock(width, height, p, "Not", type);
            case WallInFrontCondition:
                return new UIConditionBlock(width, height, p, "Wall in front", type);
            default:
                return null;

        }
    }
}
