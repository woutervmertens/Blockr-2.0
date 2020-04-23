# Game World API
Adam El M'Rabet, Kristof Schollen, Mahmoud Naif Z Fahad, Wouter Mertens

## Table of Contents
1. [Implementation](#Game-World-Implementation)
    1. [Game World](#Game-World)
    2. [Game World Type](#Game-World-Type)
2. [Client](#Game-World-Client)

## Game World Implementation

Details on how to write a valid Game World API implementation.
### Game World

[JavaDoc](../JavaDoc/com/swop/GameWorld.html)

*Example*
```java
public class GameWorldA implements GameWorld {}
```
----
**doAction**
* [JavaDoc](../JavaDoc/com/swop/GameWorld.html#doAction(com.swop.Action))
* **Parameters:**
    An Action object.
* **Implementation:**
    Perform the given Action on the GameWorld.
* **Return:**
    A SuccessState that reflects the result of the Action.
    Choosing from:
    * SUCCESS
    * FAILURE
    * GOAL_REACHED
* **Sample:**
    ```java
    @Override
    public SuccessState doAction(Action action) {
        switch (action) {
            case TURN_LEFT:
                actor.turnLeft();
                return SuccessState.SUCCESS;
                break;
            ...
        }
        ...
    }
    ```

**evaluate**
* [JavaDoc](../JavaDoc/com/swop/GameWorld.html#evaluate(com.swop.Predicate))
* **Parameters:**
    A Predicate object.
* **Implementation:**
    Evaluate the given Predicate on the GameWorld.
* **Return:**
    A boolean that reflects the result of the evaluation.
* **Sample:**
    ```java
    @Override
    public boolean evaluate(Predicate predicate) {
        switch (predicate) {
            case WALL_IN_FRONT:
                return squareInFront == Square.WALL;
                break;
            ...
        }
        return false;
    }
    ```
**createSnapshot**
* [JavaDoc](../JavaDoc/com/swop/GameWorld.html#createSnapshot()))
* **Parameters:**
    None
* **Implementation:**
    Create a Snapshot containing all the data of the current state of the GameWorld.
* **Return:**
    The created Snapshot.
* **Sample:**
    ```java
    @Override
    public Snapshot createSnapshot() {
        SnapshotA snap = new SnapshotA();
        snap.setGrid(grid);
        ...
        return snap;
    }
    ```
**restoreSnapshot**
* [JavaDoc](../JavaDoc/com/swop/GameWorld.html#restoreSnapshot(com.swop.Snapshot))
* **Parameters:**
    A Snapshot object.
* **Implementation:**
    Restore the GameWorld state from the given Snapshot
* **Return:**
    None
* **Sample:**
    ```java
    @Override
    public void restoreSnapshot(Snapshot snapshot) {
        SnapshotA snap = (SnapshotA) snapshot;
        grid = snap.getGrid();
        ...
    }
    ```
**paint**
* [JavaDoc](../JavaDoc/com/swop/GameWorld.html#paint(java.awt.Graphics,java.awt.Point))
* **Parameters:**
    A Graphics object.
    A Point object.
* **Implementation:**
    Draw the GameWorld to the screen using the Graphics object, at the location specified by the Point object.
* **Return:**
    None
* **Sample:**
    ```java
    @Override
    public void paint(Graphics g, Point position) {
        ...
        g.setColor(Color.BLACK);
        drawGrid(g);
        ...
    }
    ```

### Game World Type
[JavaDoc](../JavaDoc/com/swop/GameWorldType.html)

*Example*
```java
public class GameWorldTypeA implements GameWorldType {}
```
----

**getSupportedActions**
* [JavaDoc](../JavaDoc/com/swop/GameWorldType.html#getSupportedActions())
* **Parameters:**
    None
* **Implementation:**
    Return the Action enums supported by your doAction() implementation.
* **Return:**
    A Collection of Action enums.
    Choosing from:
    * MOVE_FORWARD
    * TURN_LEFT
    * TURN_RIGHT
    * MOVE_LEFT
    * MOVE_UP
    * MOVE_RIGHT
    * MOVE_DOWN
* **Sample:**
    ```java
    @Override
    public Collection getSupportedActions() {
        return Arrays.asList(Action.MOVE_FORWARD,Action.TURN_LEFT,Action.TURN_RIGHT);
    }
    ```

**getSupportedPredicates**
* [JavaDoc](../JavaDoc/com/swop/GameWorldType.html#getSupportedPredicates())
* **Parameters:**
    None
* **Implementation:**
    Return the Predicate enums supported by your evaluate() implementation.
* **Return:**
    A Collection of Predicate enums.
    Choosing from:
    * WALL_IN_FRONT
* **Sample:**
    ```java
    @Override
    public Collection getSupportedPredicates() {
        return Arrays.asList(Predicate.WALL_IN_FRONT);
    }
    ```

**createNewInstance**
* [JavaDoc](../JavaDoc/com/swop/GameWorldType.html#createNewInstance())
* **Parameters:**
    None
* **Implementation:**
    Return a new instance of your GameWorld implementation.
* **Return:**
    A GameWorld implementation object.
* **Sample:**
    ```java
    @Override
    public GameWorld createNewInstance() {
        GameWorldA res = new GameWorldA();
        ...
        return res;
    }
    ```
----
## Game World Client

Details on how to write a valid Game World API client.

[JavaDoc](../JavaDoc/com/swop/package-summary.html)

----
1. Add GameWorldApi.jar to the classpath.
2. Add the GameWorldAPI implementation jar to the classpath or use Class.forName to specify the implementation at runtime:
    ```java
    public static void main(String[] args){
        try {
            Class clasz = Class.forName(args[0]);
            GameWorldType gameWorldType = (GameWorldType) clasz.getConstructor().newInstance();
            ...
        } catch (...) {
            ...
        }
    }
    ```
3. Create a new GameWorld object by calling by calling *createNewInstance()* on the GameWorldType object.