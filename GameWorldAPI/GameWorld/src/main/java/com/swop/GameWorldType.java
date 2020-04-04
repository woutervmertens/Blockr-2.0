package com.swop;

import java.util.Collection;

public interface GameWorldType {
    public Collection getSupportedActions();

    public Collection getSupportedPredicates();

    public GameWorld createNewInstance();

}
