package com.swop;

public class ProgramArea {
    Program currentProgram;

    public ProgramArea(Program currentProgram) {
        this.currentProgram = currentProgram;
    }

    public Program getCurrentProgram() {
        return currentProgram;
    }

    public void setCurrentProgram(Program currentProgram) {
        this.currentProgram = currentProgram;
    }
}
