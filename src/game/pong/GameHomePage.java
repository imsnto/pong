package game.pong;

import javax.swing.*;
import java.awt.*;

public class GameHomePage extends JFrame {
    GameHomePanel panel;
    GameHomePage(){
        panel = new GameHomePanel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
