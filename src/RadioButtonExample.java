import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Radio Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel difficultyLabel = new JLabel("Difficulty:");
        JRadioButton easyButton = new JRadioButton("Easy");
        easyButton.setSelected(true); // Set "Easy" as the default selection
        JRadioButton mediumButton = new JRadioButton("Medium");
        JRadioButton hardButton = new JRadioButton("Hard");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(easyButton);
        buttonGroup.add(mediumButton);
        buttonGroup.add(hardButton);

        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Easy button selected");
                // Perform desired action or change state for "Easy" selection
            }
        });

        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Medium button selected");
                // Perform desired action or change state for "Medium" selection
            }
        });

        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hard button selected");
                // Perform desired action or change state for "Hard" selection
            }
        });

        panel.add(difficultyLabel);
        panel.add(easyButton);
        panel.add(mediumButton);
        panel.add(hardButton);

        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
