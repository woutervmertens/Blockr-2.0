package com.swop.blocks;

public class BlockFactory {
    private static BlockFactory instance = null;
    private BlockFactory(){}
    public static BlockFactory getInstance(){
        if(instance == null) instance = new BlockFactory();
        return instance;
    }

    public Block createBlockVM(BlockModel model){
        switch(model.getBlockModelType()){
            case ACTION:
                return new ActionBlock((ActionBlockModel) model);
            case CONDITION:
                return new ConditionBlock((ConditionBlockModel) model);
            case FUNCCALL:
                return new FunctionCallBlock((FunctionCallBlockModel) model);
            case FUNCDEF:
                return new FunctionDefinitionBlock((FunctionDefinitionBlockModel) model);
            case IF:
                return new IfBlock((IfBlockModel) model);
            case WHILE:
                return new WhileBlock((WhileBlockModel) model);
            case NULL:
                throw new IllegalArgumentException("Unsupported BlockModel");
        }
        return null;
    }
}
