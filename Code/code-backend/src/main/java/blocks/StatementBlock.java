package blocks;

import java.util.LinkedList;

public abstract class StatementBlock extends Block {
    LinkedList<ConditionBlock> conditions;
    BlockGroup children;

    public boolean isLoop()
    {
        return false;
    }

    public LinkedList<ConditionBlock> getConditions() {
        return conditions;
    }

    public void setConditions(LinkedList<ConditionBlock> conditions) {
        this.conditions = conditions;
    }

    public BlockGroup getChildren() {
        return children;
    }

    public void setChildren(BlockGroup children) {
        this.children = children;
    }
}
