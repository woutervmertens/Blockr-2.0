package blocks;

import java.util.LinkedList;

public abstract class StatementBlock extends Block{
    LinkedList<ConditionBlock> conditions;  // Currently either WIF or Not WIF
    BlockGroup body;

    public LinkedList<ConditionBlock> getConditions() {
        return conditions;
    }

    public void setConditions(LinkedList<ConditionBlock> conditions) {
        this.conditions = conditions;
    }

    public boolean isConditionValid() {
        if (conditions.get(0) instanceof WallInFrontBlock) {
            return true;
            // TODO: check if true ...
        } else if (conditions.get(0) instanceof NotBlock) {
            return false;
        } else {
            throw new IllegalStateException("Illegal Condition of StatementBlock !");
        }
    }

    public BlockGroup getBody() {
        return body;
    }

    public void setBody(BlockGroup children) {
        this.body = children;
    }

}
