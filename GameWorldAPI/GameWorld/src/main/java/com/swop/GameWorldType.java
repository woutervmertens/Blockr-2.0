package com.swop;

import java.util.Collection;

public interface GameWorldType {
    public Collection<Action> getSupportedActions();

    public Collection<Predicate> getSupportedPredicates();

    public GameWorld createNewInstance();

}
