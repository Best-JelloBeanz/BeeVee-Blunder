package utilz;

import inputs.KeyboardInputs;
import main.GamePanel;

import java.awt.*;

public class AirTile extends Tile{
    KeyboardInputs keyI;
    GamePanel gp;
    protected AirTile(KeyboardInputs keyI, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.keyI = keyI;
        importImg("res/tiles/grass.png");
        initHitbox();

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, (int)x, (int)y, null);
        if (keyI.hitboxVisible) {
            drawHitbox(g2);
        }
    }

}
