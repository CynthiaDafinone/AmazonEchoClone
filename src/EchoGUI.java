import javax.swing.*;

public class EchoGUI extends JFrame {

    public static void main(String[] args) {

        JFrame frame = new EchoGUI();

        frame.setLocationRelativeTo(null);
        frame.setSize(353, 325);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
