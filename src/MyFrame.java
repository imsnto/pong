import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My Frame");

        JButton button = new JButton("Click me");
        JLabel label = new JLabel("Hello, World!");

        frame.add(button);
        frame.add(label);

        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
