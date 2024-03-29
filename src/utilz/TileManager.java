package utilz;
import entities.Block;
import main.GamePanel;
import inputs.KeyboardInputs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;


public class TileManager {
    KeyboardInputs keyI;
    GamePanel gp;
    public Tile[][] tile;
    public int[][] mapTileNum;
    public TileManager(GamePanel gp, KeyboardInputs keyI) {
        this.gp = gp;
        this.keyI = keyI;
        tile = new Tile[gp.maxWorldCol][gp.maxWorldRow];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/res/maps/map02.txt");
    }

    public void loadMap(String filepath) {
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol  && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    //Generates a subclass of Tile(), differing depending on the value.
                    switch(num) {
                        case(0) -> tile[col][row] = new AirTile(gp, keyI, col, row, gp.tileSize, gp.tileSize);
                        case(1) -> tile[col][row] = new GrassTile(gp, keyI, col,row, gp.tileSize, gp.tileSize);
                        case(2) -> tile[col][row] = new BrickTile(gp, keyI,  col, row, gp.tileSize, gp.tileSize);
                        default -> tile[col][row] = new AirTile(gp, keyI, col, row, gp.tileSize, gp.tileSize);
                        // Generates an air tile if an unexpected value is detected.
                    }
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //draws the defined tiles
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        //Loops through the map, and acquires the data from it
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            if (tileNum > 0) {
                if (tile[worldCol][worldRow] != null) {
                    tile[worldCol][worldRow].draw(g2);
                }
            }
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldRow++;
                worldCol = 0;
            }
        }
    }
}
