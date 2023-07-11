import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInterface extends JFrame {
    public GameInterface() {
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 column, with spacing

        JButton playButton = new JButton("Play");
        JButton helpButton = new JButton("Help");
        JButton exitButton = new JButton("Exit");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start the game
                startGame();
            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show instructions in a dialog box
                showInstructions();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the game
                exitGame();
            }
        });

        panel.add(playButton);
        panel.add(helpButton);
        panel.add(exitButton);

        add(panel);
    }

    private void startGame() {
        // Game logic goes here
        System.out.println("Starting the game...");
    }

    private void showInstructions() {
        // Display instructions in a dialog box
        String instructions = "Welcome to the game!\n\n" +
                "Instructions:\n" +
                "- Use arrow keys to move\n" +
                "- Press spacebar to jump\n" +
                "- Collect coins to earn points\n" +
                "- Avoid obstacles to stay alive\n" +
                "\nGet ready and enjoy the game!";
        JOptionPane.showMessageDialog(this, instructions, "Game Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exitGame() {
        // Terminate the game application
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameInterface gameInterface = new GameInterface();
                gameInterface.setVisible(true);
            }
        });
    }
}
