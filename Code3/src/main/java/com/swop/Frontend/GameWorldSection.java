
import com.swop.WindowSection;

import java.awt.*;

public class GameWorldSection extends WindowSection {
    public GameWorldSection(Point pos, int width, int height) {
        super(pos, width, height);
    }

    void draw(Graphics g, GameWorld gameWorld) {
        gameWorld.paint(g, position);
    }
}
