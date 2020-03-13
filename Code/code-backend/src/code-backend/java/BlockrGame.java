import blocks.Block;
import worldElements.Character;
import worldElements.Direction;
import worldElements.GameWorld;
import worldElements.Square;

public class BlockrGame {
    private GameWorld gameWorld;
    private Program program;

    public BlockrGame() {
        gameWorld = new GameWorld(new int[]{5,5},new int[]{3,4});
        gameWorld.setGrid(createGrid());
        gameWorld.setCharacter(createCharacter());
        program = new Program(gameWorld);
    }

    private Character createCharacter() {
        Character ch = new Character(new int[]{1, 4});
        ch.setDirection(Direction.RIGHT);
        return ch;
    }

    private Square[][] createGrid(){
        Square[][] grid = new Square[5][5];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Square(false);
            }
        }
        grid[4][1] = new Square(true);
        grid[4][2] = new Square(true);
        grid[3][2] = new Square(true);
        grid[3][3] = new Square(true);
        grid[3][4] = new Square(true);
        return grid;
    }

    public Character executeBlock() {
        program.execute();
        return gameWorld.getCharacter();
    }

    public Character getCharacter(){
        return gameWorld.getCharacter();
    }

    public Square[][] getGrid()
    {
        return gameWorld.getGrid();
    }

    public void reset()
    {
        gameWorld.reset();
        program.reset();
    }


    public void addBlockToBlockGroudp(Block block) {
        program.getBlockGroup().addBlockAtEnd(block);
    }
}
