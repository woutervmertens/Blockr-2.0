package com.swop;

import java.awt.*;

public interface GameWorld {
    public SuccessState doAction(Action action);

    public boolean evaluate(Predicate predicate);

    public Snapshot createSnapshot();

    public void restoreSnapshot(Snapshot snapshot);

    public void paint(Graphics g, Point position);
}
