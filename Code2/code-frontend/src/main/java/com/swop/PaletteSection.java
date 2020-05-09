package com.swop;

import com.swop.handlers.BlockrGameFacade;
import com.swop.uiElements.BlockType;
import com.swop.uiElements.BlockTypes;
import com.swop.uiElements.UIBlock;

import java.awt.*;
import java.util.Collection;

public class PaletteSection extends WindowSection {
    private BlockTypes[] types;
    private BlockrGameFacade blockrGameFacade;

    public PaletteSection(Point pos, int width, int height, BlockrGameFacade blockrGameFacade){
        super(pos, width, height);
        this.blockrGameFacade = blockrGameFacade;
    }

    public void draw(Graphics g, boolean isPaletteHidden) {
        //Background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(position.x, position.y, width, height);

        if (isPaletteHidden) return;

        int x = 15;
        int y = 10;
        Collection<Action> gwActions = blockrGameFacade.getSupportedActions();
        Collection<Predicate> gwPredicates = blockrGameFacade.getSupportedPredicates();
        types = new BlockTypes[gwActions.size() + gwPredicates.size() + 3];
        int k = 0;
        //Supported actions
        for (Action a : gwActions) {
            types[k] = new BlockTypes(a.toString(), 110, BlockType.ActionType);
            types[k].setAction((Action)a);
            k++;
        }
        //Supported predicates
        for (Predicate p : gwPredicates) {
            types[k] = new BlockTypes(p.toString(), 40, BlockType.Predicate);
            types[k].setPredicate(p);
            k++;
        }
        //Blockr types
        types[k++] = new BlockTypes("Not", 40, BlockType.NotCondition);
        types[k++] = new BlockTypes("If", 110, BlockType.IfStatement);
        types[k++] = new BlockTypes("While", 110, BlockType.WhileStatement);

        int step = height / types.length;
        for (int i = 0; i < types.length; i++) {
            UIBlock uiBlock = types[i].getNewUIBlock(x, y + step * i);
            uiBlock.setPosition(new Point(x, y + step * i));
            g.setColor(Color.black);
            g.drawRoundRect(5, step * i, width - 10, step, 5, 5);
            drawBlock(uiBlock, g);
        }
    }

    /**
     * Get type of the area clicked on (assuming that the click is on the palette area.
     *
     * @pre isWithin(x, y);
     */
    public BlockTypes getTypeOfClick(int x, int y) {
        assert isWithin(x, y);

        int s = types.length;
        int i = y / (getHeight() / s);
        BlockTypes bt = null;
        System.out.println(i);
        if (i < s)
            bt = types[i];
        return bt;

//        BlockTypes[] types = BlockTypes.values();
//        int step = height / types.length;
//
//        int i = Math.floorDiv(PALETTE.getHeight(), y);
    }
}
