package com.swop;

import java.awt.*;

public interface GameWorld {
    /**
     * Performs an action.
     * @param action The action to perform.
     * @return The resulting SuccessState of the action performed.
     */
    public SuccessState doAction(Action action);

    /**
     * Evaluates a predicate.
     * @param predicate The predicate to evaluate.
     * @return The boolean result of the evaluation.
     */
    public boolean evaluate(Predicate predicate);

    /**
     * Creates a snapshot of the GameWorld data.
     * @return The created Snapshot.
     */
    public Snapshot createSnapshot();

    /**
     * Restores the data to match that of the given snapshot.
     * @param snapshot The Snapshot source to get the data from.
     */
    public void restoreSnapshot(Snapshot snapshot);

    /**
     * Paints the GameWorld to the screen.
     * @param g Graphics object needed to draw.
     * @param position Point where to draw the GameWorld.
     */
    public void paint(Graphics g, Point position);
}
