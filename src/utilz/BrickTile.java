package utilz;

import inputs.KeyboardInputs;
import main.GamePanel;

import java.awt.*;

public class BrickTile extends Tile{

    KeyboardInputs keyI;

    GamePanel gp;
    protected BrickTile(GamePanel gp, KeyboardInputs keyI, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.keyI = keyI;
        this.gp = gp;
        initHitbox();
        importImg("/res/tiles/bricks.png");

    }
    public void draw(Graphics2D g2) {
        g2.drawImage(image, (int)x, (int)y, gp.tileSize, gp.tileSize, null);
        if (keyI.hitboxVisible) {
            drawHitbox(g2);
        }
    }
}
