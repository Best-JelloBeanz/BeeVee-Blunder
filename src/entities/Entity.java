package entities;

import main.GamePanel;
import utilz.Tile;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public float x, y, velocityX, velocityY, accelerationY, accelerationX, deceleration;
    public float maxVelocityY, maxVelocityX;
    protected int width;
    public int height;
    protected int scale;
    public boolean canMoveLeft, canMoveRight, canMoveUp, canMoveDown = true;
    public Rectangle hitbox;
    public BufferedImage right, left;
    public String direction;
    GamePanel gp;
    //Entity constructor
    protected Entity(GamePanel gp, float x, float y, int width, int height, int scale) {
        this.gp = gp;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.width = width * scale;
        this.height = height * scale;
        initHitbox(width, height);
    }
    public void drawHitbox(Graphics g) {
        //Debugging hitbox
        g.setColor(Color.red);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
    public void initHitbox(int hitWidth, int hitHeight) {
        hitbox = new Rectangle((int) x, (int) y, hitWidth, hitHeight);
    }
    protected void updateHitbox() {
        hitbox.x = (int) x + 3;
        hitbox.y = (int) y + 1;
    }
    protected void TileCollision() {
        int worldCol = 0;
        int worldRow = 0;
        //Loops through the map, and acquires the data from it
        Entity a = this;
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = gp.tileM.mapTileNum[worldCol][worldRow];
            Tile b = gp.tileM.tile[worldCol][worldRow];
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldRow++;
                worldCol = 0;
            }
            if (tileNum != 0) {
                b.collision(a);
            }
        }
    }
    //Leftover code from first testing phase
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