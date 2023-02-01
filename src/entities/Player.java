package entities;
import main.GamePanel;
import inputs.KeyboardInputs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import main.Game;
import utilz.TileManager;

public class Player extends Entity{
    KeyboardInputs keyI;
    GamePanel gp;
    Game game;


    public Player(GamePanel gp, KeyboardInputs keyI, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.gp = gp;
        this.keyI = keyI;
        setDefaultValues();
        importImg();
        initHitbox();
    }
    public void setDefaultValues() {
        x = 100;
        y = 100;
        accelerationY = 100f;
        accelerationX = 350f;
        deceleration = 400f;
        maxVelocityX = 200;
        maxVelocityY = 300;
        direction = "right";
    }
    private void importImg() {
        InputStream l = getClass().getResourceAsStream("/res/BeeVee_left.png");
        InputStream r = getClass().getResourceAsStream("/res/BeeVee_right.png");
        try{
            right = ImageIO.read(r);
            left = ImageIO.read(l);
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                r.close();
                l.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void updatePos(float deltaTime) {
        // update the player's position based on the input...
        if (keyI.spacePressed) {
            velocityY -= 1.5 * accelerationY * deltaTime;
        }
        else {
            // Update the player's position based on the velocity
            if (velocityY < 0) {
                velocityY += 1.5 * accelerationY * deltaTime;
            }
            velocityY += accelerationY * deltaTime;
            if (velocityY > maxVelocityY || velocityY < -maxVelocityY) {
                if (velocityY > 0) {
                    velocityY = maxVelocityY;
                }
                if (velocityY < 0) {
                    velocityY = -maxVelocityY;
                }
            }
            y += velocityY * deltaTime;
        }
        if (keyI.leftPressed) {
            direction = "left";
            if (canMoveLeft) {
                velocityX -= accelerationX * deltaTime;
            }
        }
        else if (keyI.rightPressed) {
            direction = "right";
            if (canMoveRight) {
                velocityX += accelerationX * deltaTime;
            }
        }
        else {
            // Decelerate the player if the left or right keys are not pressed
            if (velocityX > 0) {
                if (!canMoveLeft) {
                    velocityX = 0;
                }
                velocityX -= deceleration * deltaTime;
                if (velocityX < 0) velocityX = 0;
            } else if (velocityX < 0) {
                if (!canMoveRight) {
                    velocityX = 0;
                }
                velocityX += deceleration * deltaTime;
                if (velocityX > 0) velocityX = 0;
            }
        }
        if (velocityX > maxVelocityX || velocityX < -maxVelocityX) {
            if (velocityX > 0) {
                velocityX = maxVelocityX;
            }
            if (velocityX < 0) {
                velocityX = -maxVelocityX;
            }
        }
        x += velocityX * deltaTime;

    }
    public void TileCollision() {
        int worldCol = 0;
        int worldRow = 0;
        //Loops through the map, and acquires the data from it
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = gp.tileM.mapTileNum[worldCol][worldRow];
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldRow++;
                worldCol = 0;
            }
            if (tileNum == 2) {
                if (gp.player.hitbox.intersects(gp.tileM.e[worldCol][worldRow].hitbox)) {
                    if (gp.player.y > gp.tileM.e[worldCol][worldRow].y - gp.player.height) {
                        gp.player.y = gp.tileM.e[worldCol][worldRow].y - gp.player.height;
                    }
                    if (gp.player.velocityY > 0) {
                        gp.player.velocityY = 0;
                    }
                    gp.player.grounded = true;
                }
                else {
                    gp.player.grounded = false;
                }
            }
        }
    }
    public void update(float deltaTime) {
        updatePos(deltaTime);
        updateHitbox();

        TileCollision();
        isColliding(gp.player, gp.block);

    }

    public void draw(Graphics2D g2) {
        BufferedImage img = null;
        switch(direction) {
            case "right" -> img = right;
            case "left" -> img = left;
        }
        g2.drawImage(img, (int)x, (int)y, null);
        if (keyI.hitboxVisible) {
            drawHitbox(g2);
        }
    }
}
