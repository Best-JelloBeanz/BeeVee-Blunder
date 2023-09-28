package entities;
import java.awt.*;
import main.GamePanel;
//
public class Block extends Entity{
    public Block(GamePanel gp, float x, float y, int width, int height, int scale) {
        super(gp, x, y, width, height, scale);

    }
    private void setDefaultValues() {
        x = 200;
        y = 100;
        accelerationY = 80f;
        accelerationX = 250f;
        deceleration = 340f;
    }
    private void updatePos() {
        y += 2;
    }

    @Override
    protected void updateHitbox() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    public void update() {
        updatePos();
        updateHitbox();
        TileCollision();
    }
    public void draw(Graphics2D g2) {
        drawHitbox(g2);
    }
}