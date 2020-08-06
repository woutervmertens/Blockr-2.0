package com.swop.blocks;

/**
 * Singleton object which creates respective viewmodels for each supported BlockModel type.
 */
public class BlockFactory {
    private static BlockFactory instance = null;
    private BlockFactory(){}
    public static BlockFactory getInstance(){
        if(instance == null) instance = new BlockFactory();
        return instance;
    }

    /**
     * Creates a new ViewModel for the given BlockModel
     * @param model the BlockModel to create a ViewModel for
     * @return a BlockViewModel
     */
    public BlockVM createBlockVM(BlockModel model){
        if(model == null) return null;
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
            default:
                throw new IllegalArgumentException("Unsupported BlockModel");
        }
    }
}
