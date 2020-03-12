import blocks.ActionBlock;
import blocks.Block;
import blocks.MoveBlock;
import blocks.TurnBlock;
import blocks.BlockGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    private Object LinkedList;

    @BeforeEach
    void setUp() {

//        TODO: er waren fouten: verbeter
//        MoveBlock mBlock1 = new MoveBlock();
//
//        TurnBlock tblock1 = new TurnBlock();
//
//        BlockGroup group1 = new BlockGroup(null , null);
//        group1.setBlocks((List<Block>) mBlock1);
//        group1.setPosition(mBlock1.getPosition());
//
//        Program program1 = new Program(group1);
//
//        ProgramArea pArea1 = new ProgramArea()

    }

    @Test
    void execute() throws Exception{
        try {
            execute();
        }catch (Exception e) {
             System.out.println("More than 1 program in program area");
        }
    }
}