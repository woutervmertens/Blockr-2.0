import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RobotGameWorld implements GameWorld {
    private Square[][] grid;
    private int size;
    private Point drawPosition;
    private Robot robot;

    public RobotGameWorld() {
        this.grid = new Square[5][5];
        this.size = 30;
        this.drawPosition = new Point(0,0);
        this.robot = new Robot();
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Square[][] getGrid() {
        return grid;
    }

    public int getSize() {
        return size;
    }

    public Point getDrawPosition() {
        return drawPosition;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setDrawPosition(Point drawPosition) {
        this.drawPosition = drawPosition;
    }

    public void setRobot(Direction direction, int[] position)
    {
        if(this.robot == null) return;
        this.robot.setPosition(position);
        this.robot.setDirection(direction);
    }

    /**
     * Sets up a world grid
     * @param gridSize The horizontal and vertical length of the grid
     * @param wallPositions List of int[]{y,x} positions of the walls in the grid
     * @param goalPosition The int[]{y,x} position of the goal
     */
    public void setGrid(int gridSize, ArrayList<int[]> wallPositions, int[] goalPosition){
        if(grid == null || grid[0].length != gridSize)
            grid = new Square[gridSize][gridSize];
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(grid[i],Square.AIR);
        }
        for(int[] wallpos : wallPositions)
        {
            grid[wallpos[0]][wallpos[1]] = Square.WALL;
        }
        grid[goalPosition[0]][goalPosition[1]] = Square.GOAL;
    }

    @Override
    public SuccessState doAction(Action action) {
        switch (action) {
            case MOVE_FORWARD:
                robot.setPosition(robot.getPosititionInFront());
                break;
            case TURN_LEFT:
                robot.turnLeft();
                break;
            case TURN_RIGHT:
                robot.turnRight();
                break;
        }
        return evaluatePosition(); //does not reset on failure
    }

    private SuccessState evaluatePosition() {
        int[] robotPosition = robot.getPosition();
        //is in bounds?
        if(!(robotPosition[0] >= 0
                && robotPosition[0] < grid.length
                && robotPosition[1] >= 0
                && robotPosition[1] < grid[0].length)){
            return SuccessState.FAILURE;
        }
        //are we on a wall/on goal?
        Square gridSquare = getGridSquareByPosition(robotPosition);
        switch (gridSquare){
            case WALL:
                return SuccessState.FAILURE;
            case GOAL:
                return SuccessState.GOAL_REACHED;
        }
        return SuccessState.SUCCESS;
    }

    private Square getGridSquareByPosition(int[] position){
        return grid[position[0]][position[1]];
    }


    @Override
    public boolean evaluate(Predicate predicate) {
        switch (predicate) {
            case WALL_IN_FRONT:
                return getGridSquareByPosition(robot.getPosititionInFront()) == Square.WALL;
        }
        return false;
    }

    @Override
    public Snapshot createSnapshot() {
        RobotSnapshot snap = new RobotSnapshot();
        snap.setGrid(grid);
        snap.setRobot(robot);
        return snap;
    }

    @Override
    public void restoreSnapshot(Snapshot snapshot) {
        RobotSnapshot snap = (RobotSnapshot) snapshot;
        grid = snap.getGrid();
        robot = snap.getRobot();
    }

    @Override
    public void paint(Graphics g) {
        drawGrid(g);
        drawCharacter(g,getOffPos(robot.getPosition()[1],robot.getPosition()[0]));
    }

    private void drawGrid(Graphics g) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                switch (grid[i][j]){
                    case WALL:
                        drawWall(g,getOffPos(j,i));
                        break;
                    case GOAL:
                        drawFlag(g,getOffPos(j,i));
                        break;
                }
            }
        }
    }

    /**
     * Takes grid positions and returns the pixel position offset to the grid position
     * @param x Grid x position
     * @param y Grid y position
     * @return Point of pixel position
     */
    private Point getOffPos(int x, int y){
        return new Point((x*size) + drawPosition.x,(y*size) + drawPosition.y);
    }

    private void drawWall(Graphics g,Point offPos){
        g.setColor(Color.DARK_GRAY);

        Polygon p = new Polygon();
        p.addPoint(offPos.x + 5,        offPos.y + 5);
        p.addPoint(offPos.x + size - 5, offPos.y + 5);
        p.addPoint(offPos.x + size - 5, offPos.y + size - 5);
        p.addPoint(offPos.x + 5,        offPos.y + size - 5);

        g.drawPolygon(p);
    }
    private void drawFlag(Graphics g,Point offPos){
        g.setColor(new Color(0,150,50));

        Polygon p = new Polygon();
        p.addPoint(offPos.x + 5,              offPos.y + 5);
        p.addPoint(offPos.x + size - 5,       offPos.y + 5);
        p.addPoint(offPos.x + size - 5,       offPos.y + size - 5);
        p.addPoint(offPos.x + size/2 + 1,     offPos.y + size - 5);
        p.addPoint(offPos.x + size/2 + 1,     offPos.y + 2*size/3);
        p.addPoint(offPos.x + 2*(size/3) + 1, offPos.y + size/2);
        p.addPoint(offPos.x + size/2 + 1,     offPos.y + size/3);
        p.addPoint(offPos.x + size/2 - 1,     offPos.y + size/3);
        p.addPoint(offPos.x + size/2 - 1,     offPos.y + size - 5);
        p.addPoint(offPos.x + 5,              offPos.y + size - 5);

        g.drawPolygon(p);
    }
    private void drawCharacter(Graphics g,Point offPos) {
        g.setColor(Color.RED);

        Polygon p = new Polygon();
        switch (robot.getDirection()) {
            case UP:
                p.addPoint(offPos.x + size / 2, offPos.y + 5);           //TopMiddle
                p.addPoint(offPos.x + size - 5, offPos.y + size - 5);    //RightBottom
                p.addPoint(offPos.x + 5,        offPos.y + size - 5);    //LeftBottom
                break;
            case RIGHT:
                p.addPoint(offPos.x + 5,        offPos.y + 5);           //LeftTop
                p.addPoint(offPos.x + size - 5, offPos.y + size / 2);    //RightMiddle
                p.addPoint(offPos.x + 5,        offPos.y + size - 5);    //LeftBottom
                break;
            case DOWN:
                p.addPoint(offPos.x + 5,        offPos.y + 5);           //LeftTop
                p.addPoint(offPos.x + size - 5, offPos.y + 5);           //RightTop
                p.addPoint(offPos.x + size / 2, offPos.y + size - 5);    //BottomMiddle
                break;
            case LEFT:
                p.addPoint(offPos.x + size - 5, offPos.y + 5);           //RightTop
                p.addPoint(offPos.x + size - 5, offPos.y + size - 5);    //RightBottom
                p.addPoint(offPos.x + 5,        offPos.y + size / 2);    //LeftMiddle
                break;
        }
        g.drawPolygon(p);
    }
}
