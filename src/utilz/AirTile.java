package utilz;

import inputs.KeyboardInputs;
import main.GamePanel;

import java.awt.*;

public class AirTile extends Tile{
    KeyboardInputs keyI;
    GamePanel gp;
    protected AirTile(GamePanel gp, KeyboardInputs keyI, int col, int row, int width, int height) {
        super(col, row, width, height);
        this.keyI = keyI;
        this.gp = gp;
        importImg("/res/tiles/grass.png");
        initHitbox();

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, (int)x, (int)y, gp.tileSize, gp.tileSize, null);
    }

}
