package com.swop.command;

import com.swop.ProgramArea;

public abstract class ProgramAreaCommand  implements ICommand{
    ProgramArea programArea = ProgramArea.getInstance();
}
