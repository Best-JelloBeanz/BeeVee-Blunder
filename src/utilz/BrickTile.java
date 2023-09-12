package utilz;

import inputs.KeyboardInputs;
import main.GamePanel;

import java.awt.*;

public class BrickTile extends Tile{

    KeyboardInputs keyI;

    GamePanel gp;
    protected BrickTile(KeyboardInputs keyI, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.keyI = keyI;
        initHitbox();
        importImg("/res/tiles/bricks.png");

    }
    public void draw(Graphics2D g2) {
        g2.drawImage(image, (int)x, (int)y, null);
        if (keyI.hitboxVisible) {
            drawHitbox(g2);
        }
    }
}
