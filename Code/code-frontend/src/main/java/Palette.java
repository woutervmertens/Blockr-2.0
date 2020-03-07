import java.awt.*;
import java.util.ArrayList;

public class Palette {

    int blockheight = 30;
    int height = 600;
    int width = 200;
    ArrayList<PaletteButton> paletteButtons = new ArrayList<>();
    public Palette(int width, int height, int blockheight)
    {
        this.width = width;
        this.height = height;
        this.blockheight = blockheight;
    }

    public void draw(Graphics g, Point pos)
    {
        paletteButtons = new ArrayList<>();
        int blockspace = blockheight + 10;
        //Background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(pos.x, pos.y, width, height);

        //BlockButtons
        //action
        paletteButtons.add(new PaletteButton(Color.RED,"Move forward",BlockTypes.MoveForward));
        paletteButtons.add(new PaletteButton(Color.RED,"Turn Left",BlockTypes.TurnLeft));
        paletteButtons.add(new PaletteButton(Color.RED,"Turn Right",BlockTypes.TurnRight));
        //statement
        paletteButtons.add(new PaletteButton(Color.ORANGE,"If statement",BlockTypes.IfStatement));
        paletteButtons.add(new PaletteButton(Color.ORANGE,"While statement",BlockTypes.WhileStatement));
        //condition
        paletteButtons.add(new PaletteButton(Color.CYAN,"NOT",BlockTypes.NotCondition));
        paletteButtons.add(new PaletteButton(Color.CYAN,"Wall in front",BlockTypes.WallInFrontCondition));

        int s = paletteButtons.size();
        for (int i = 0; i < s; i++) {
            g.setColor(paletteButtons.get(i).getBackgroundColor());
            g.fillRoundRect(pos.x + 10,pos.y + 10 + blockspace*i,width - 20,blockheight,5,5);
            g.setColor(paletteButtons.get(i).getFontColor());
            g.drawString(paletteButtons.get(i).getText(),pos.x + 55,pos.y + blockheight + blockspace*i);
        }
    }

    public BlockTypes getBlockTypeClicked(int y) {
        float f = y / (blockheight + 10);
        int i = (int)f;
        BlockTypes bt = BlockTypes.INVALIDTYPE;
        System.out.println(i);
        if(i < paletteButtons.size())
            bt = paletteButtons.get(i).getType();
        return bt;
    }

    public int getWidth()
    {
        return width;
    }

    private class PaletteButton
    {
        private Color backgroundColor;
        private Color fontColor = Color.BLACK;
        private String text;
        private BlockTypes type;
        public PaletteButton(Color bgC, String txt, BlockTypes t)
        {
            backgroundColor = bgC;
            text = txt;
            type = t;
        }

        public Color getBackgroundColor() {
            return backgroundColor;
        }

        public Color getFontColor() {
            return fontColor;
        }

        public String getText() {
            return text;
        }
        public BlockTypes getType(){
            return type;
        }
    }
}
