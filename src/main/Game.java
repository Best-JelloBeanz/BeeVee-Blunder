package main;
//Center game operations

import entities.Player;
import java.awt.*;

public class Game implements Runnable{
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Thread gameThread;
    public static final int FPS_SET = 120;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();

    }
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }



    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now;
        while (true) {
            now = System.nanoTime();
            gamePanel.deltaTime = (now - lastFrame) / 1000000000.0f;
            if(System.nanoTime() - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
            }
            try {
                Thread.sleep(1); // sleep for a small amount of time to reduce CPU usage
            } catch (InterruptedException e) {
                // handle exception
            }
        }
    }
}

