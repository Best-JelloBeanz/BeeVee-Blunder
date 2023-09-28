package utilz;

import entities.Entity;
import inputs.KeyboardInputs;
import main.GamePanel;

import java.awt.*;

public class GrassTile extends Tile{
    KeyboardInputs keyI;
    GamePanel gp;
    protected GrassTile(GamePanel gp, KeyboardInputs keyI, int col, int row, int width, int height) {
        super(col, row, width, height);
        this.keyI = keyI;
        this.gp = gp;
        initHitbox();
        importImg("/res/tiles/grass.png");
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
            //If to the right of the tile
            //noinspection IntegerDivisionInFloatingPointContext
            if (a.hitbox.x >= b.x + (b.width / 2)) {
                a.canMoveLeft = false;
                //Prevents player from maintaining momentum when turning around.
                a.velocityX = 0;
                a.x = b.x + b.width - 3;
            }
            //If to the left of the tile
            //noinspection IntegerDivisionInFloatingPointContext
            if ((a.hitbox.x + a.hitbox.width) <= b.x + (b.width / 2)) {
                a.canMoveRight = false;
                //Prevents player from maintaining momentum when turning around
                a.velocityX = 0;
                a.x = b.x - a.hitbox.width - 2;
            }
        }
    }
}
