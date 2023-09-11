package utilz;
import inputs.KeyboardInputs;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.InputStreamReader;



public class Tile {
    KeyboardInputs keyI;

    public BufferedImage image;
    public Rectangle hitbox;
    public boolean collision = false;
    public float x, y;
    public int width, height;

    protected Tile(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        initHitbox();
    }

    /*
    public void collision(Entity rec1, Tile rec2) {
        if (tileNum == 1)
    }
     */
    protected void importImg(String img) {
        InputStream i = getClass().getResourceAsStream(img);
        //image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(img)));
        try {
            assert i != null;
            image = ImageIO.read(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initHitbox() {
        hitbox = new Rectangle((int) x, (int) y, width, height);
    }

    protected void updateHitbox() {
        hitbox.x = (int) x + 3;
        hitbox.y = (int) y + 1;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, (int)x, (int)y, null);
        if (keyI.hitboxVisible) {
            drawHitbox(g2);
        }
    }

    void drawHitbox(Graphics g) {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
        g.setColor(Color.black);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

    }
}
