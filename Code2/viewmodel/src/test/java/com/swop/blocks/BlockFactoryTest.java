package com.swop.blocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BlockFactoryTest {
    StdBlockData s;

    @BeforeEach
    void setUp() {
        s = new StdBlockData(new Point(1,1),0,0,"");
    }

    @Test
    void createBlockVM() {
        BlockVM b = BlockFactory.getInstance().createBlockVM(new ActionBlockModel(s,null));
        assertEquals(BlockModelType.ACTION,b.model.getBlockModelType());
        b = BlockFactory.getInstance().createBlockVM(new ConditionBlockModel(s,false,null));
        assertEquals(BlockModelType.CONDITION,b.model.getBlockModelType());
        b = BlockFactory.getInstance().createBlockVM(new FunctionCallBlockModel(s,null));
        assertEquals(BlockModelType.FUNCCALL,b.model.getBlockModelType());
        b = BlockFactory.getInstance().createBlockVM(new FunctionDefinitionBlockModel(s));
        assertEquals(BlockModelType.FUNCDEF,b.model.getBlockModelType());
        b = BlockFactory.getInstance().createBlockVM(new IfBlockModel(s,0));
        assertEquals(BlockModelType.IF,b.model.getBlockModelType());
        b = BlockFactory.getInstance().createBlockVM(new WhileBlockModel(s,0));
        assertEquals(BlockModelType.WHILE,b.model.getBlockModelType());
        b = BlockFactory.getInstance().createBlockVM(null);
        assertEquals(null,b);
    }
}