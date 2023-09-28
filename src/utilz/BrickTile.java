package utilz;

import entities.Entity;
import inputs.KeyboardInputs;
import main.GamePanel;

import java.awt.*;

public class BrickTile extends Tile{

    KeyboardInputs keyI;

    GamePanel gp;
    protected BrickTile(GamePanel gp, KeyboardInputs keyI, int col, int row, int width, int height) {
        super(col, row, width, height);
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

    public void collision(Entity a) {
        Tile b = gp.tileM.tile[col][row];
        if (a.hitbox.intersects(b.hitbox)) {
            //If colliding, and not standing on the tile
            if (a.y > b.y - a.height) {

                a.y = b.hitbox.y - a.height;
            }
            //Resets velocity so momentum is not carried when walking off
            if (a.velocityY > 0) {
                a.velocityY = 0;
            }
            //Does not reset if velocity goes upwards to make jumping possible
        }
    }


}
