package entities;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import inputs.KeyboardInputs;
import main.Game;
import main.GamePanel;

public class Block extends Entity{
    GamePanel gp;
    public Block(GamePanel gp, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.gp = gp;
        //d dd setDefaultValues();
        initHitbox();
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