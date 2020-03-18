package com.swop.windowElements;

import com.swop.BlockrGame;
import com.swop.handlers.BackendConverter;
import com.swop.uiElements.UICharacter;
import com.swop.uiElements.UIGridElement;

import java.awt.*;

public class UIGameWorld {
    private Point pos;
    private UIGridElement[][] grid;
    private int elementSize;
    private BlockrGame blockrGame;  // TODO remove this direct dependency and then refactor to com.swop.windowElements package
    private UICharacter uiCharacter;
    private BackendConverter converter;

    public UIGameWorld(Point pos, int elementSize, BlockrGame blockrGame) {
        this.elementSize = elementSize;
        this.pos = pos;
        this.blockrGame = blockrGame;
        converter = new BackendConverter();
        getUiCharacter();
    }

    public void setUiCharacter(UICharacter uiCharacter) {
        this.uiCharacter = uiCharacter;
    }

    private void getUiCharacter() {
        setUiCharacter(converter.convertCharacter(blockrGame.getCharacter()));
    }

    public UIGridElement[][] getGrid() {
        return grid;
    }

    /**
     * Sets the game world grid and a backup
     *
     * @param grid The grid info to set
     */
    public void setGrid(UIGridElement[][] grid) {
        this.grid = grid;
    }

    /**
     * For each grid element, draws the polygon in the elements color.
     *
     * @param g awt Graphics
     */
    public void draw(Graphics g) {
        getUiCharacter();
        if (grid == null) return;
        for (UIGridElement[] elCol : grid) {
            for (UIGridElement el : elCol) {
                g.setColor(el.getColor());
                g.drawPolygon(el.getPolygon(elementSize, pos));
            }
        }
        g.setColor(uiCharacter.getColor());
        g.drawPolygon(uiCharacter.getPolygon(elementSize, pos));
    }

    public UICharacter getCharacter() {
        getUiCharacter();
        return uiCharacter;
    }
}
