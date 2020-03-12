package worldElements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    private int x = 9;
    private int y = 9;
    private int startX = 0;
    private int startY = 9;
    private int[] startPos = {startY, startX};
    private int[] size = {y,x};
    private int[] posGoal = {0,9};
    private GameWorld world1 = new GameWorld(size, posGoal);
    // Direction kerel to the right
    private Character kerel = new Character(startPos);
    private Character kerelEdge = new Character(size);
    //wall
    private Square square1 = new Square(false);
    //grounds
    private Square square2 = new Square(true);
    private Square square3 = new Square(true);
    private Square square4 = new Square(true);
    private Square[][] small = {{square1, square2},{square3 , square4}};


    @BeforeEach
    void setUp(){
        world1.setCharacter(null);
        kerel.setPosition(startPos);
    }




    @Test
    void canMoveForward() {
        world1.setCharacter(kerel);
        kerel.move(Direction.RIGHT);
        int[] newPos = {startY, startX + 1};
        assertEquals(newPos, kerel.getPosition());
    }

    @Test
    void playerOnEdgeOfField() throws Exception{
        world1.setCharacter(kerelEdge);
        try {
            kerelEdge.move(Direction.RIGHT);
        }catch (Exception e){
            System.out.println("Player can't go outside the world");
        }

    }

    @Test
    //whole world will be changed to 2x2
    void WallInFrontOfPlayer() throws Exception {
        world1.setGrid(small);
        world1.setCharacter(kerel);
        try {
            kerel.move(Direction.UP);
        } catch (Exception e){
            System.out.println("player can't move to a wall");
        }


    }
}