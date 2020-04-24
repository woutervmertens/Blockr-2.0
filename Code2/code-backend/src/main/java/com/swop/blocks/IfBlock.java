package com.swop.blocks;

import java.awt.*;

public class IfBlock extends StatementBlock {
    public IfBlock(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void execute() {
        if (isBusy() || isConditionValid()) {
            //1st
            if (!isBusy()) {
                setBusy(true);
                setNextCurrent();
                //check of volgende null is zo niet -> volgende als current zetten
            }
            executeBlock();

        } else {
            setDone(true);
            setBusy(false);
        }
    }

    private void executeBlock() {
        if (getCurrent() == null) {
            setDone(true);
            setBusy(false);
        } else {
            Executable exBlock = (Executable) getCurrent();
            exBlock.execute();
            setNextCurrent();
            if (getCurrent() == null) {
                setDone(true);
                setBusy(false);
            }

        }
    }


}
