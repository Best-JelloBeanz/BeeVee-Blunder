package main;
//Window operations

import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow extends JFrame {
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel){
        jframe = new JFrame();
        jframe.setSize(1280, 870);
        jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // jframe.setIconImage();
        //jframe.setResizable(false);
        jframe.setTitle("Hi");
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);//jframe spawns at screen center
        //jframe.pack();
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
