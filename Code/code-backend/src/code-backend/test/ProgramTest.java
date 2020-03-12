import blocks.Block;
import blocks.MoveBlock;
import blocks.TurnBlock;
import blocks.BlockGroup;
import org.junit.jupiter.api.*;
import worldElements.Character;
import worldElements.Direction;
import worldElements.GameWorld;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgramTest {

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


@BeforeEach
    void setup(){
    kerel.setDirection(Direction.RIGHT);
    kerel.setPosition(startPos);
    world1.setCharacter(kerel);
}


    @Test
    void executeP0() {
        Program program0 = new Program(null);
        ProgramArea pArea0 = new ProgramArea(program0);

        pArea0.getCurrentProgram().execute();

        assertEquals(startPos,kerel.getPosition(),"kerel has moved while programArea empty");
        assertEquals(Direction.RIGHT,kerel.getDirection(), "Kerel has changed direction while programArea is empty.");

    
    }
    @Test
    void executeP1() {
        MoveBlock mBlock1 = new MoveBlock();
        BlockGroup group1 = new BlockGroup(null);
        group1.setBlocks((List<Block>) mBlock1);
        /*
        program with 1 blockgroup that only contains moveForward
         */
        Program program1 = new Program(group1);
        ProgramArea pArea1 = new ProgramArea(program1);

        pArea1.getCurrentProgram().execute();

        int[] newPos = {startY, startX + 1};

        assertEquals(newPos,kerel.getPosition(),"kerel isn't at (9,1)");
        assertEquals(Direction.RIGHT,kerel.getDirection(), "Kerel has changed direction while program doesn't have a turnBlock.");


    }
    @Test
    void executeP2(){
        TurnBlock tblock1 = new TurnBlock(Direction.LEFT);
        BlockGroup group2 = new BlockGroup(null);
        group2.setBlocks((List<Block>) tblock1);
        /*
        program with 1 blockgroup that only contains turnBlock
         */
        Program program2 = new Program(group2);
        ProgramArea pArea2 = new ProgramArea(program2);

        pArea2.getCurrentProgram().execute();

        Direction newDirection = Direction.UP;

        assertEquals(startPos,kerel.getPosition(),"kerel has moved while program doesn't have a moveBlock");
        assertEquals(Direction.UP,kerel.getDirection(), "Kerel hasn't changed direction to UP.");

    }
}