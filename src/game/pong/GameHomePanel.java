package game.pong;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameHomePanel extends JPanel {
    static final Dimension SCREEN_SIZE = new Dimension(1000, (int) (1000* (5.0 / 9.0)));
    JButton playButton;
    JButton helpButton;
    JButton highScoreButton;
    JButton exitButton;

    BufferedImage backgroundImage;

    GameHomePanel(){

        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.setLayout(new CardLayout());
        this.setLayout(null);

        ImageIcon playIcon = new ImageIcon("images/play.png");
        ImageIcon settingIcon = new ImageIcon("images/help.jpg");

        playButton = new JButton("Play");
        helpButton = new JButton("Help");
        highScoreButton = new JButton("High Score");
        exitButton = new JButton("Exit");


        Font buttonFont = playButton.getFont();
        Font largerFont = buttonFont.deriveFont(buttonFont.getSize() + 8f);

        playButton.setFont(largerFont);
        helpButton.setFont(largerFont);
        highScoreButton.setFont(largerFont);
        exitButton.setFont(largerFont);

        playButton.setBounds(400, 170, 200, 50);
        helpButton.setBounds(400, 220, 200, 50);
        highScoreButton.setBounds(400, 270, 200, 50);
        exitButton.setBounds(400, 320, 200, 50);


        //playButton.setIcon(playIcon);
       // settingButton.setIcon(settingIcon);

        playButton.setBorderPainted(false);
        helpButton.setBorderPainted(false);
        highScoreButton.setBorderPainted(false);
        exitButton.setBorderPainted(false);


        playButton.setContentAreaFilled(false);
        helpButton.setContentAreaFilled(false);
        highScoreButton.setContentAreaFilled(false);
        exitButton.setContentAreaFilled(false);


        try {
            backgroundImage = ImageIO.read(new File("images/bg2.jpg")); // Replace "path_to_your_image_file.jpg" with the actual path to your image file
        } catch (IOException e) {
            e.printStackTrace();
        }

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GameFrame frame = new GameFrame();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        highScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showInstructions();
            }
        });

        add(playButton);
        add(helpButton);
        add(highScoreButton);
        add(exitButton);
    }

    private void showInstructions() {
        // Display instructions in a dialog box
        String instructions = "Welcome to the game!\n\n" +
                "Instructions:\n" +
                "Up key - Use up arrow key to right-paddle move up\n" +
                "Space key- Press space bar to pause/resume game\n" +
                "Down key -  Press down arrow key to move right-paddle lower\n" +
                "W key - Press W key to left-paddle move up\n" +
                "S key - Press S Key to left paddle move lower" +
                "\nGet ready and enjoy the game!";
        JOptionPane.showMessageDialog(this, instructions, "Game Instructions", JOptionPane.INFORMATION_MESSAGE);
    }


    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //this.setBackground(Color.black);
        if (backgroundImage != null) {
            int x = (this.getWidth() - backgroundImage.getWidth()) / 2;
            int y = (this.getHeight() - backgroundImage.getHeight()) / 2;
            g.drawImage(backgroundImage, 0,0, null);
        }

    }
}
