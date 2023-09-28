package main;
//The display in the window
import entities.*;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import utilz.TileManager;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Graphics;


public class GamePanel extends JPanel{
    final int originalTileSize = 16;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    public final int maxWorldCol = 30;
    public final int maxWorldRow = 30;
    public float deltaTime;
    MouseInputs mouseI = new MouseInputs();
    public KeyboardInputs keyI = new KeyboardInputs(this);
    public Player player = new Player(this, keyI, 0, 0, 16, 16, scale);
    public TileManager tileM = new TileManager(this, keyI);
    public Block block = new Block(this, 200, 200, 32, 32, scale);

    // GAME STATE
    public int gameState = 1;
    public final int playState = 1;
    public final int pauseState = 2;
    public GamePanel() {
        this.addKeyListener(keyI);
        this.addMouseListener(mouseI);
        this.addMouseMotionListener(mouseI);
    }

    public void updateGame() {
        if (gameState == playState) {
            player.update(deltaTime);
            block.update();
        }
        if (gameState == pauseState) {
            //nothing
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        updateGame();
        tileM.draw(g2);
        player.draw(g2);
        block.draw(g2);
        g2.dispose();
    }
}
