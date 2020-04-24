package com.swop.command;

import com.swop.ProgramArea;

public abstract class ProgramAreaCommand implements ICommand {
    protected ProgramArea programArea = ProgramArea.getInstance();
}
