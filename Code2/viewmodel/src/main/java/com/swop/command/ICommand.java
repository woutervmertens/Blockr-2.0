package com.swop.command;

/**
 * The command interface.
 */
public interface ICommand {
    void execute();

    void undo();
}
