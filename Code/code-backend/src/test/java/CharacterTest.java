import com.swop.worldElements.Character;
import com.swop.worldElements.GameWorld;
import com.swop.worldElements.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CharacterTest {
    private int x = 9;
    private int y = 9;
    private int startX = 0;
    private int startY = 8;
    private int[] startPos = {startX, startY};
    private int[] size = {x-1, y-1};
    private int[] posGoal = {8, 0};
    private GameWorld world1 = new GameWorld(size, posGoal);
    // Direction kerel to the right
    private Character kerel = new Character(startPos);
    private Character kerelEdge = new Character(size);
    private Character smallKerel = new Character(new int[]{0, 0});

    Square[][] grid = new Square[9][9];



    //wall
    private Square square3 = new Square(false);
    //grounds
    private Square square1 = new Square(true);
    private Square square2 = new Square(true);
    private Square square4 = new Square(true);
    private Square[][] small = {{square1, square2}, {square3, square4}};


    @BeforeEach
    void setUp() {
        kerel.setPosition(startPos);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Square(true);
            }
        }
        world1.setGrid(grid);
    }


    @Test
    void canMoveForward() {
        world1.setCharacter(kerel);
        world1.move();
        int[] newPos = {startX +1, startY};
        int[] actualPos = kerel.getPosition();
        assertEquals(newPos[0], actualPos[0],"Kerel did move up or down");
        assertEquals(newPos[1], actualPos[1],"Kerel didn't move to the right");
    }

    @Test
    void playerOnEdgeOfField() throws Exception {
        world1.setCharacter(kerelEdge);
        world1.move();
        assertEquals(8, world1.getCharacter().getPosition()[0],"Kerel moved over the edge");
        assertEquals(8, world1.getCharacter().getPosition()[1],"Kerel moved up or down");

    }

    @Test
        //whole world will be changed to 2x2
    void WallInFrontOfPlayer() throws Exception {
        world1.setGrid(small);
        world1.setCharacter(smallKerel);
        world1.move();
        assertEquals(0, world1.getCharacter().getPosition()[0],"smallKerel moved on the wall");
        assertEquals(0, world1.getCharacter().getPosition()[1],"smallKerel moved up or down");



    }
}