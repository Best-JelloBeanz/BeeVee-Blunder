package utilz;

import main.GamePanel;

import java.awt.*;

public class BrickTile extends Tile{

    GamePanel gp;
    protected BrickTile(GamePanel gp, float x, float y, int width, int height) {
        super(x, y, width, height);
        initHitbox();
        this.gp = gp;
        importImg("res/tiles/bricks.png");
        System.out.println("Bricks");

    }
    public void draw(Graphics2D g2) {
        g2.drawImage(image, (int)x, (int)y, null);
        if (keyI.hitboxVisible) {
            drawHitbox(g2);
        }
    }
}
