package com.swop.blocks;

import com.swop.GameWorld;

import java.awt.*;


public class WhileBlock extends StatementBlock {

    public WhileBlock(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void execute() {
        if (isBusy() || isConditionValid()){
            if (!isBusy()){
                setBusy(true);
                setNextCurrent();
                executeBlock();
            }else{
                executeBlock();
            }
        }else{
            setDone(true);
            setBusy(false);
        }
    }

    private void executeBlock() {
        if (getCurrent() == null){
            setDone(true);
            setBusy(false);
        }else{
            Executable exBlock = (Executable)getCurrent();
            exBlock.execute();
            setNextCurrent();
            if (getCurrent()== null){
                if (isConditionValid()){
                    setCurrent(bodyBlocks.get(0));
                }else{
                setDone(true);
                setBusy(false);
                }
            }
        }
    }
}
