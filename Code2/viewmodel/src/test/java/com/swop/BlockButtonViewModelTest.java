package com.swop;

import com.swop.blocks.ActionBlockModel;
import com.swop.blocks.StdBlockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BlockButtonViewModelTest {

    BlockButtonViewModel blockButton;

    @BeforeEach
    void setUp() {
        blockButton = new BlockButtonViewModel(new BlockButtonModel(new Point(0,0),10,10,new ActionBlockModel(new StdBlockData(new Point(78,1),5,5,""),null)),null);
    }

    @Test
    void handleClick() {
        blockButton.HandleClick(80,80);
        assertThrows(NullPointerException.class,()->blockButton.HandleClick(5,5));
    }

    @Test
    void getBGPolygon() {
        assertNotNull(blockButton.getBGPolygon());
    }

    @Test
    void getBGColor() {
        assertNotNull(blockButton.getBGColor());
    }

    @Test
    void getBlock() {
        assertNotNull(blockButton.getBlock());
        assertEquals(15,blockButton.getBlock().getPosition().x);
    }
}