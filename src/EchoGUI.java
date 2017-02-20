import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class EchoGUI extends JFrame {

    final PowerButton power = new PowerButton("POW");

    ////temporary code for implementaion of power button
    private class PowerButton extends JButton {

        PowerButton(String s) {
            //  setIcon(new ImageIcon("keycap" + s + ".png"));
            setBorder(null);
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    //change color of button here
                    //add function to do stuff here
                }
            });
        }
    }

    //adds background images and button locations
    public EchoGUI() {
        setTitle("The Amazon Echo");
        setContentPane(new JLabel(new ImageIcon("../resources/echo.png")));
        setLayout(null);

        power.setBounds(330, 360, 40, 40);
        add(power);
    }

    //sets up frame and give certain values
    public static void main(String[] argv) {
        JFrame frame = new EchoGUI();
        frame.setLocationRelativeTo(null);
        frame.setSize(700, 700);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}