package com.swop.command;

public abstract class ProgramAreaCommand implements ICommand {
    protected ProgramArea programArea;

    public ProgramAreaCommand(ProgramArea programArea) {
        this.programArea = programArea;
    }
}
