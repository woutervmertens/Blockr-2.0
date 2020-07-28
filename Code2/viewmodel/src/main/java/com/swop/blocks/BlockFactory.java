package com.swop.blocks;

public class BlockFactory {
    private static BlockFactory instance = null;
    private BlockFactory(){}
    public static BlockFactory getInstance(){
        if(instance == null) instance = new BlockFactory();
        return instance;
    }

    public BlockVM createBlockVM(BlockModel model){
        switch(model.getBlockModelType()){
            case ACTION:
                return new ActionBlockVM((ActionBlockModel) model);
            case CONDITION:
                return new ConditionBlockVM((ConditionBlockModel) model);
            case FUNCCALL:
                return new FunctionCallBlockVM((FunctionCallBlockModel) model);
            case FUNCDEF:
                return new FunctionDefinitionBlockVM((FunctionDefinitionBlockModel) model);
            case IF:
                return new IfBlockVM((IfBlockModel) model);
            case WHILE:
                return new WhileBlockVM((WhileBlockModel) model);
            case NULL:
                throw new IllegalArgumentException("Unsupported BlockModel");
        }
        return null;
    }
}
