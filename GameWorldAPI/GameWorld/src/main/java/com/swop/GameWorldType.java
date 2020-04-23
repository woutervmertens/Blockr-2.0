package com.swop;

import java.util.Collection;

public interface GameWorldType {
    /**
     * Returns the Actions supported by the GameWorld.doAction(Action) method.
     * @return A Collection of Actions.
     */
    public Collection<Action> getSupportedActions();

    /**
     * Returns the Predicates supported by the GameWorld.evaluate(Predicate) method.
     * @return A Collection of Predicates.
     */
    public Collection<Predicate> getSupportedPredicates();

    /**
     * Creates a new instance of the GameWorld.
     * @return A new GameWorld instance.
     */
    public GameWorld createNewInstance();

}
