package entities;
import java.awt.*;
import main.GamePanel;
//
public class Block extends Entity{
    GamePanel gp;
    public Block(GamePanel gp, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.gp = gp;
        initHitbox(width, height);
    }
    public void setDefaultValues() {
        x = 200;
        y = 100;
        accelerationY = 80f;
        accelerationX = 250f;
        deceleration = 340f;
    }
    public void draw(Graphics2D g2) {
        drawHitbox(g2);
    }
}