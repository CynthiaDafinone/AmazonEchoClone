package MainPackage;

import javax.swing.*;

public class echoGUI extends JFrame {

    public static void main(String[] args) {

        JFrame frame = new echoGUI();

        frame.setLocationRelativeTo(null);
        frame.setSize(353, 325);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
