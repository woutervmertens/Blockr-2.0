import com.swop.Program;
import com.swop.ProgramArea;
import com.swop.blocks.*;
import com.swop.worldElements.Character;
import com.swop.worldElements.Direction;
import com.swop.worldElements.GameWorld;
import com.swop.worldElements.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgramTest {

    private int x = 9;
    private int y = 9;
    private int startX = 0;
    private int startY = 8;
    private int[] startPos = {startX, startY};
    private int[] size = {x, y};
    private int[] posGoal = {8, 0};
    private GameWorld world1 = new GameWorld(size, posGoal);
    // Direction kerel to the right
    private Character kerel = new Character(startPos);
    Square[][] grid = new Square[9][9];
    private Character smallKerel = new Character(new int[]{0, 0});

    //wall
    private Square square3 = new Square(false);
    //grounds
    private Square square1 = new Square(true);
    private Square square2 = new Square(true);
    private Square square4 = new Square(true);
    private Square[][] small = {{square1, square2}, {square3, square4}};


    @BeforeEach
    void setup() {
        kerel.setDirection(Direction.RIGHT);
        kerel.setPosition(startPos);
        world1.setCharacter(kerel);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Square(true);
            }
        }
        world1.setGrid(grid);
    }


    @Test
    void executeP0() {
        Program program0 = new Program(null);
        ProgramArea pArea0 = new ProgramArea(program0);

        // TODO: 19.03.20 implement that nothing happens happen if user wants to execute with empty program area
        pArea0.getCurrentProgram().execute();

        assertEquals(startPos[0], kerel.getPosition()[0], "kerel has moved while programArea empty");
        assertEquals(startPos[1], kerel.getPosition()[1], "kerel has moved while programArea empty");
        assertEquals(Direction.RIGHT, kerel.getDirection(), "Kerel has changed direction while programArea is empty.");


    }

    @Test
    void executeP1() {
        MoveBlock mBlock1 = new MoveBlock();
        LinkedList<Block> lList = new LinkedList<>();
        lList.add(mBlock1);
        BlockGroup group1 = new BlockGroup();
        group1.setBlocks(lList);
        /*
        program with 1 blockgroup that only contains moveForward
         */
        Program program1 = new Program(world1, group1, new LinkedList<>());
        ProgramArea pArea1 = new ProgramArea(program1);

        pArea1.getCurrentProgram().execute();

        int[] newPos = {startX + 1, startY};

        assertEquals(newPos[0], kerel.getPosition()[0], "kerel isn't at x == 1");
        assertEquals(newPos[1], kerel.getPosition()[1], "kerel isn't at y == 8");
        assertEquals(Direction.RIGHT, kerel.getDirection(), "Kerel has changed direction while program doesn't have a turnBlock.");


    }

    @Test
    void executeP2() {
        TurnBlock tblock1 = new TurnBlock(Direction.LEFT);
        LinkedList<Block> lList = new LinkedList<>();
        lList.add(tblock1);
        BlockGroup group2 = new BlockGroup();
        group2.setBlocks(lList);
        /*
        program with 1 blockgroup that only contains turnBlock
         */
        Program program2 = new Program(world1, group2, new LinkedList<>());
        ProgramArea pArea2 = new ProgramArea(program2);

        pArea2.getCurrentProgram().execute();

        Direction newDirection = Direction.UP;

        assertEquals(startPos, kerel.getPosition(), "kerel has moved while program doesn't have a moveBlock");
        assertEquals(newDirection, kerel.getDirection(), "Kerel hasn't changed direction to UP.");

    }

    @Test
    void executeP3() {
        TurnBlock tblock2 = new TurnBlock(Direction.LEFT);
        MoveBlock mBlock2 = new MoveBlock();
        BlockGroup group3 = new BlockGroup();
        group3.addBlockAtEnd(tblock2);
        group3.addBlockAtEnd(mBlock2);

        /*
        program with 1 blockgroup that contains turnBlock and moveBlock
         */
        Program program3 = new Program(world1, group3, new LinkedList<>());
        ProgramArea pArea3 = new ProgramArea(program3);

        pArea3.getCurrentProgram().execute();

        Direction newDirection = Direction.UP;
        assertEquals(newDirection, kerel.getDirection(), "Kerel hasn't changed direction to UP.");

        pArea3.getCurrentProgram().execute();

        int[] newPos = {startX , startY -1};

        assertEquals(newPos[0], world1.getCharacter().getPosition()[0], "kerel isn't at x == 0");
        assertEquals(newPos[1], world1.getCharacter().getPosition()[1], "kerel isn't at y == 8");


    }

    @Test
    void executeP4() {
        world1.setGrid(small);
        world1.setCharacter(smallKerel);

        TurnBlock tBlock2 = new TurnBlock(Direction.LEFT);
        BlockGroup group = new BlockGroup();
        group.addBlockAtEnd(tBlock2);

        IfBlock ifBlock1 = new IfBlock();
        ifBlock1.setBody(group);
        BlockGroup group4 = new BlockGroup();
        group4.addBlockAtEnd(ifBlock1);

        WallInFrontBlock wBlock1 = new WallInFrontBlock();
        LinkedList<ConditionBlock> conditions = new LinkedList<>();
        conditions.add(wBlock1);
        ifBlock1.setConditions(conditions);

        Program program4 = new Program(world1, group4, new LinkedList<>());  // TODO: check linkedlist
        ProgramArea pArea4 = new ProgramArea(program4);
        //if
        pArea4.getCurrentProgram().execute();
        //turn
        pArea4.getCurrentProgram().execute();


        assertEquals(0, world1.getCharacter().getPosition()[0], "kerel moved without moveBlock");
        assertEquals(0, world1.getCharacter().getPosition()[1], "kerel moved without moveBlock");
        assertEquals(Direction.UP, world1.getCharacter().getDirection(), "Kerel has changed direction while program doesn't have a turnBlock.");
    }
}