package utilz;
import entities.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Tile {

    public BufferedImage image;
    public Rectangle hitblox;
    public boolean collision = false;
    public float x, y;
    protected int width, height;

    protected Tile() {
        width = 32;
        height = 32;
        initHitblox();
    }
    public void drawHitblox(Graphics g, int x, int y) {
        //Debugging hitbox
        hitblox.x = x;
        hitblox.y = y;
        g.setColor(Color.black);
        g.drawRect(hitblox.x, hitblox.y, hitblox.width, hitblox.height);

    }
    /*
    public void collision(Entity rec1, Tile rec2) {
        if (tileNum == 1)
    }
     */
    public void initHitblox() {
        hitblox = new Rectangle((int) x, (int) y, width, height);
    }

    protected void updateHitbox() {
        hitblox.x = (int) x + 3;
        hitblox.y = (int) y + 1;
    }
}
