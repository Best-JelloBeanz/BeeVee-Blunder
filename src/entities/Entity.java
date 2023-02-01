package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public float x, y, velocityX, velocityY, accelerationY, accelerationX, deceleration;
    public float maxVelocityY, maxVelocityX;
    protected int width, height;
    public boolean grounded = false;
    public boolean canMoveLeft = true;
    public boolean canMoveRight = true;
    public Rectangle hitbox;

    public BufferedImage right, left;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    protected Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitbox();
    }
    public void drawHitbox(Graphics g) {
        //Debugging hitbox
        g.setColor(Color.red);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
    public void initHitbox() {
        hitbox = new Rectangle((int) x, (int) y, width, height);
    }
    protected void updateHitbox() {
        hitbox.x = (int) x + 3;
        hitbox.y = (int) y + 1;
    }


    protected void isColliding(Entity rec1, Entity rec2) {
        if (rec1.hitbox.intersects(rec2.hitbox)) {
            if (rec1.x >= rec2.x + (rec2.width / 2)) {
                canMoveLeft = false;
                rec1.x = rec2.x + rec2.width;
            }
            else if ((rec1.x + rec1.width) <= rec2.x + (rec2.width / 2)) {
                canMoveRight = false;
                rec1.x = rec2.x - rec1.width - 3;
            }
        }
        else {
            canMoveLeft = true;
            canMoveRight = true;
        }
    }
}