package entities;
import main.GamePanel;
import inputs.KeyboardInputs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Entity{
    KeyboardInputs keyI;
    GamePanel gp;
    //The Player constructor
    public Player(GamePanel gp, KeyboardInputs keyI, float x, float y, int width, int height) {
        super(x, y, width, height);
        //I know how this works and why I need this, but I can't describe it very well.
        this.gp = gp;
        //Loads keyboard input functionality
        this.keyI = keyI;
        setDefaultValues();
        //Loads the player sprites
        importImg();
        //Creates a new instance of hitbox for the player to use
        initHitbox();
    }
    //Only needs to be called once, gives initial values for the player to spawn with
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
    //Only needs to be called once, loads player sprites
    private void importImg() {
        //Original sprite images
        InputStream l = getClass().getResourceAsStream("/res/BeeVee_left.png");
        InputStream r = getClass().getResourceAsStream("/res/BeeVee_right.png");
        try{
            assert r != null;
            right = ImageIO.read(r);
            assert l != null;
            left = ImageIO.read(l);
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert r != null;
                r.close();
                assert l != null;
                l.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //Updates player position when called
    private void updatePos(float deltaTime) {
        if (keyI.spacePressed) {
            //
            velocityY -= 1.5 * accelerationY * deltaTime;
        }
        else {
            if (velocityY < 0) {
                velocityY += 1.5 * accelerationY * deltaTime;
            }
            velocityY += accelerationY * deltaTime;
            //If not exceeding maximum velocity in the up or down directions
            if (velocityY > maxVelocityY || velocityY < -maxVelocityY) {
                if (velocityY > 0) {
                    velocityY = maxVelocityY;
                }
                if (velocityY < 0) {
                    velocityY = -maxVelocityY;
                }
            }
            //Update y position
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
        //Decelerate the player if the left and right keys are not pressed
        else {
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
        //Prevents the player from going above maxVelocityX
        if (velocityX > maxVelocityX || velocityX < -maxVelocityX) {
            if (velocityX > 0) {
                velocityX = maxVelocityX;
            }
            if (velocityX < 0) {
                velocityX = -maxVelocityX;
            }
        }
        //Movement
        x += velocityX * deltaTime;

    }
    //Checks for tile collisions and executes collision rules accordingly when called
    //Original
    public void TileCollision() {
        int worldCol = 0;
        int worldRow = 0;
        //Loops through the map, and acquires the data from it
        Player a = gp.player;
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = gp.tileM.mapTileNum[worldCol][worldRow];
            Block b = gp.tileM.e[worldCol][worldRow];
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldRow++;
                worldCol = 0;
            }
            //If tile is grass
            if (tileNum == 1) {
                if (a.hitbox.intersects(b.hitbox)) {
                    //If to the right of the tile
                    //noinspection IntegerDivisionInFloatingPointContext
                    if (a.x >= b.x + (b.width / 2)) {
                        canMoveLeft = false;
                        //Prevents player from maintaining momentum when turning around.
                        a.velocityX = 0;
                        a.x = b.x + b.width -3;
                    }
                    //If to the left of the tile
                    //noinspection IntegerDivisionInFloatingPointContext
                    if ((a.x + a.width) <= b.x + (b.width / 2)) {
                        canMoveRight = false;
                        //Prevents player from maintaining momentum when turning around
                        a.velocityX = 0;
                        a.x = b.x - a.width - 2;
                    }
                }
                else {
                    canMoveLeft = true;
                    canMoveRight = true;
                }
            }
            //If tile is brick
            if (tileNum == 2) {
                if (a.hitbox.intersects(b.hitbox)) {
                    //If colliding, and not standing on the tile
                    if (a.y > b.y - a.height) {
                        a.y = b.y - a.height;
                    }
                    //Resets velocity so momentum is not carried when walking off
                    if (a.velocityY > 0) {
                        a.velocityY = 0;
                    }
                    //Does not reset if velocity goes upwards to make jumping possible
                }
            }
        }
    }
    //Updates the player when called
    public void update(float deltaTime) {
        updatePos(deltaTime);
        updateHitbox();
        TileCollision();
        isColliding(gp.player, gp.block);

    }
    //Draws the character when called
    public void draw(Graphics2D g2) {
        BufferedImage img = null;
        switch(direction) {
            case "right" -> img = right;
            case "left" -> img = left;
        }
        g2.drawImage(img, (int)x, (int)y, null);
        //Debug hitbox
        if (keyI.hitboxVisible) {
            drawHitbox(g2);
        }
    }
}
