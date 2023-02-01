package main;
//Window operations

import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
public class GameWindow extends JFrame {
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel){
        jframe = new JFrame();
        jframe.setSize(gamePanel.tileSize * gamePanel.maxWorldCol, gamePanel.tileSize * gamePanel.maxWorldRow);
        jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);

        jframe.setResizable(false);
        jframe.setTitle("Hi");
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);//jframe spawns at screen center
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                System.out.println("Hi!");
            }
            @Override
            public void windowLostFocus(WindowEvent e) {
                System.out.println("Bye!");
            }
        });

    }
}
